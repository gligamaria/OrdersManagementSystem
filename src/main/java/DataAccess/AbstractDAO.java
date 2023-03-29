package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * The class prepares queries for the statements and methods to run them as well.
 * It is an abstract class which can later on be used by all the types of tables.
 * It also uses reflection in order to get the contents of the table
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    private int columnsNumber;
    /**
     * returns the number of columns of the table
     */
    public int getColumnsNumber() {
        return columnsNumber;
    }
    @SuppressWarnings("unchecked")
    /**
     * constructor which initialises the private variable type
     * and also sets the number of columns
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            columnsNumber = metadata.getColumnCount();

        } catch (SQLException e) {
            System.out.println("Could not retrieve database metadata " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * The method creates the select query, with "?", that
     * will later on be replaced by variables
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" =? ");
        return sb.toString();
    }
    /**
     * The method creates the select all query, with "?", that
     * will later on be replaced by variables
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName()).append(" ");
        return sb.toString();
    }

    /**
     * The method creates the insert query, with "?", that
     * will later on be replaced by variables
     */
    private String createInsertQuery() {

        //INSERT INTO table_name (column1, column2, column3, ...)
        //VALUES (value1, value2, value3, ...);

        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(type.getSimpleName()).append(" (");
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                if (index != type.getDeclaredFields().length - 2) {
                    sb.append(field.getName()).append(", ");
                } else {
                    sb.append(field.getName()).append(") ");
                }
                index++;
            }
        }
        sb.append("VALUES (");
        index = 0;
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                if (index != type.getDeclaredFields().length - 2) {
                    sb.append(" ? " + ", ");
                } else {
                    sb.append(" ? " + "); ");
                }
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * The method creates the update query, with "?", that
     * will later on be replaced by variables
     */
    private String createUpdateQuery() {

//        UPDATE table_name
//        SET column1 = value1, column2 = value2, ...
//        WHERE condition = ?;

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        int index = 0;
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                if (index != type.getDeclaredFields().length - 1) {
                    sb.append(field.getName()).append(" = ?, ");
                } else {
                    sb.append(field.getName()).append(" = ? ");
                }
            }
            index++;
        }
        sb.append("WHERE id = ?; ");
        return sb.toString();
    }

    /**
     * The method creates the delete query, with "?", that
     * will later on be replaced by variables
     */
    private String createDeleteQuery(String condition) {
        //DELETE FROM table_name WHERE condition;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(type.getSimpleName()).append(" WHERE ");
        sb.append(condition).append(" =?; ");
        return sb.toString();
    }

    /**
     * The method returns the contents of the table as a List of T type.
     * It uses the select all query in order to get all the data
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The method returns the instance of the table which has the
     * desired id. The method uses the select query, which uses as
     * a condition the id field
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The method returns a list of type T, having
     * been given a resultSet. In order to do that,
     * it uses reflection.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
                InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * The method inserts a new instance in the table,
     * making use of the insert query previously defined
     */
    public T insert(T instance) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int index = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    String fieldName = field.getName();
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getReadMethod();
                    statement.setObject(index, method.invoke(instance));
                    index++;
                }
            }
            statement.executeUpdate();
        } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The method updates the instance with the given id with the
     * other values from the given instances. To achieve that, it
     * uses the update query statement, with the id pasted as the
     * condition.
     */
    public T update(T instance, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int index = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    String fieldName = field.getName();
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getReadMethod();
                    statement.setObject(index, method.invoke(instance));
                    index++;
                }
            }
            statement.setObject(index, id);
            statement.executeUpdate();

        } catch (SQLException | IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The method deletes the instances passed as a parameter,
     * using the delete query
     */
    public T delete(T instance, String condition) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(condition);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals(condition)) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    statement.setObject(1, method.invoke(instance));
                    statement.executeUpdate();
                }
            }

        } catch (SQLException | IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The method return the header of the columns
     * as an array of type String in order to use
     * it when declaring a JTable
     */
    public String[] getColumns() {

        List<String> columns = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();
            System.out.println("test_table columns: ");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve database metadata " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return columns.toArray(new String[0]);
    }

    /**
     * The method return the data of the table
     * as a matrix of type String in order to use
     * it when declaring a JTable
     */
    public String[][] getData() {
        String[][] data = new String[1][1];
        try {
            int index_row = 0;
            List<T> instances = findAll();
            data = new String[instances.size()][getColumnsNumber()];
            for (T instance : instances) {
                int index_column = 0;
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object object = method.invoke(instance);
                    data[index_row][index_column] = object.toString();
                    index_column++;
                }
                index_row++;
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:getData " + e.getMessage());
        }
        return data;
    }
}