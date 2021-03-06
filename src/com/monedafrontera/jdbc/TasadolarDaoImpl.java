/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.monedafrontera.jdbc;

import com.monedafrontera.dao.*;
import com.monedafrontera.factory.*;
import java.util.Date;
import com.monedafrontera.dto.*;
import com.monedafrontera.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;

public class TasadolarDaoImpl extends AbstractDAO implements TasadolarDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT codigopais, fecha, tasa, tasadolar, trm FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( codigopais, fecha, tasa, tasadolar, trm ) VALUES ( ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET codigopais = ?, fecha = ?, tasa = ?, tasadolar = ?, trm = ? WHERE codigopais = ? AND fecha = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE codigopais = ? AND fecha = ?";

	/** 
	 * Index of column codigopais
	 */
	protected static final int COLUMN_CODIGOPAIS = 1;

	/** 
	 * Index of column fecha
	 */
	protected static final int COLUMN_FECHA = 2;

	/** 
	 * Index of column tasa
	 */
	protected static final int COLUMN_TASA = 3;

	/** 
	 * Index of column tasadolar
	 */
	protected static final int COLUMN_TASADOLAR = 4;

	/** 
	 * Index of column trm
	 */
	protected static final int COLUMN_TRM = 5;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/** 
	 * Index of primary-key column codigopais
	 */
	protected static final int PK_COLUMN_CODIGOPAIS = 1;

	/** 
	 * Index of primary-key column fecha
	 */
	protected static final int PK_COLUMN_FECHA = 2;

	/** 
	 * Inserts a new row in the tasadolar table.
	 */
	public TasadolarPk insert(Tasadolar dto) throws TasadolarDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			stmt.setString( index++, dto.getCodigopais() );
			stmt.setTimestamp(index++, dto.getFecha()==null ? null : new java.sql.Timestamp( dto.getFecha().getTime() ) );
			if (dto.isTasaNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getTasa() );
			}
		
			if (dto.isTasadolarNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getTasadolar() );
			}
		
			if (dto.isTrmNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getTrm() );
			}
		
			//System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			//System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TasadolarDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the tasadolar table.
	 */
	public void update(TasadolarPk pk, Tasadolar dto) throws TasadolarDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + dto );
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setString( index++, dto.getCodigopais() );
			stmt.setTimestamp(index++, dto.getFecha()==null ? null : new java.sql.Timestamp( dto.getFecha().getTime() ) );
			if (dto.isTasaNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getTasa() );
			}
		
			if (dto.isTasadolarNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getTasadolar() );
			}
		
			if (dto.isTrmNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getTrm() );
			}
		
			stmt.setString( 6, pk.getCodigopais() );
			stmt.setTimestamp(7, pk.getFecha()==null ? null : new java.sql.Timestamp( pk.getFecha().getTime() ) );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TasadolarDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the tasadolar table.
	 */
	public void delete(TasadolarPk pk) throws TasadolarDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_DELETE + " with PK: " + pk );
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setString( 1, pk.getCodigopais() );
			stmt.setTimestamp(2, pk.getFecha()==null ? null : new java.sql.Timestamp( pk.getFecha().getTime() ) );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TasadolarDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the tasadolar table that matches the specified primary-key value.
	 */
	public Tasadolar findByPrimaryKey(TasadolarPk pk) throws TasadolarDaoException
	{
		return findByPrimaryKey( pk.getCodigopais(), pk.getFecha() );
	}

	/** 
	 * Returns all rows from the tasadolar table that match the criteria 'codigopais = :codigopais AND fecha = :fecha'.
	 */
	public Tasadolar findByPrimaryKey(String codigopais, Date fecha) throws TasadolarDaoException
	{
		Tasadolar ret[] = findByDynamicSelect( SQL_SELECT + " WHERE codigopais = ? AND fecha = ?", new Object[] { codigopais, fecha==null ? null : new java.sql.Timestamp( fecha.getTime() ) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the tasadolar table that match the criteria 'codigopais = :codigopais'.
	 */
	public Tasadolar[] findWhereCodigopaisEquals(String codigopais) throws TasadolarDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE codigopais = ? ORDER BY codigopais", new Object[] { codigopais } );
	}

	/**
	 * Method 'TasadolarDaoImpl'
	 * 
	 */
	public TasadolarDaoImpl()
	{
	}

	/**
	 * Method 'TasadolarDaoImpl'
	 * 
	 * @param userConn
	 */
	public TasadolarDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "public.tasadolar";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Tasadolar fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Tasadolar dto = new Tasadolar();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Tasadolar[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Tasadolar dto = new Tasadolar();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Tasadolar ret[] = new Tasadolar[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Tasadolar dto, ResultSet rs) throws SQLException
	{
		dto.setCodigopais( rs.getString( COLUMN_CODIGOPAIS ) );
		dto.setFecha( rs.getTimestamp(COLUMN_FECHA ) );
		dto.setTasa( rs.getFloat( COLUMN_TASA ) );
		if (rs.wasNull()) {
			dto.setTasaNull( true );
		}
		
		dto.setTasadolar( rs.getFloat( COLUMN_TASADOLAR ) );
		if (rs.wasNull()) {
			dto.setTasadolarNull( true );
		}
		
		dto.setTrm( rs.getFloat( COLUMN_TRM ) );
		if (rs.wasNull()) {
			dto.setTrmNull( true );
		}
		
	}
        
        /**
     * Retorna la TRM anterior a la fecha actual
     *
     * @return
     */
    public Tasadolar getTrmAnterior() {
        Tasadolar temp = null;

        try {
            //el metodo getLastPk() obtiene la ultima llave para el mayor 
            //dolar registrado antes al dia actual
            temp = this.findByPrimaryKey(getLastPk());
//                Log.imprimirLog(new Date(), "TEST", "Dolar colombia anterior " + temp.getFecha());
        } catch (TasadolarDaoException e) {
            e.printStackTrace();
        }

        return temp;
    }

    /**
     * Este metodo retorna la llave primaria de un objeto Tasadolar para la
     * ultima tasa de dolar al dia actual
     *
     * @return
     */
    protected TasadolarPk getLastPk() {
        TasadolarPk pk = null;

        //obtengo el ultimo dolar anterior al dia actual
        String sql = "SELECT tasadolar.codigopais, "
                + "max(tasadolar.fecha) as fecha "
                + "FROM tasadolar WHERE "
                + "tasadolar.codigopais = 'CO' AND  "
                + "tasadolar.fecha < CURRENT_DATE "
//                           + "tasadolar.fecha < CURRENT_DATE - INTERVAL '2 DAY' "
                + "GROUP BY tasadolar.codigopais";

        //campos que hacen parte de la llave primaria
        String codigoPais = "";
        Date fecha = null;
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = isConnSupplied ? userConn : ResourceManager.getConnection();
            final String SQL = sql;
            stmt = conn.prepareStatement(SQL);
            stmt.setMaxRows(maxRows);
            rs = stmt.executeQuery();
 
            while (rs.next()) {
                codigoPais = rs.getString(1);
                fecha = rs.getDate(2);
            }
            pk = new TasadolarPk(codigoPais, fecha);//creo el nuevo pk
        } catch (Exception _e) {
            System.out.println("Error al ejecutar la consulta...");
            _e.printStackTrace();
        } finally {//cerrando recursos
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
        }
        return pk;
    }

    /**
     * Retorna la TRM correspondiente al viernes
     *
     * @return
     */
    public Tasadolar getTrmViernes(Date diaAnterior, int diasARestar) {
        Tasadolar temp = null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaAnterior); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, -diasARestar);// (-)los dias recibidos
        
        try {
            temp = this.findByPrimaryKey(
                    getPkViernes(  calendar.getTime()));
        } catch (TasadolarDaoException e) {
            e.printStackTrace();
        }
        return temp;
    }
    

    /**
     * Este metodo retorna la llave primaria de un objeto Tasadolar para la
     * ultima tasa de dolar al dia actual
     *
     * @return
     */
    protected TasadolarPk getPkViernes( Date fec) {
        TasadolarPk pk = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
        //obtengo la llave primaria con base a la fecha dada
        String sql = "SELECT tasadolar.codigopais, "
                + "max(tasadolar.fecha) as fecha "
                + "FROM tasadolar WHERE "
                + "tasadolar.codigopais = 'CO' AND  "
                + "tasadolar.fecha = '" +  sdf.format(fec) +"'"
                + "GROUP BY tasadolar.codigopais";
//        System.out.println("Ejecutando Query: " + sql);
        //campos que hacen parte de la llave primaria
        String codigoPais = "";
        Date fecha = null;
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = isConnSupplied ? userConn : ResourceManager.getConnection();
            final String SQL = sql;
            stmt = conn.prepareStatement(SQL);
            stmt.setMaxRows(maxRows);
            rs = stmt.executeQuery();

            while (rs.next()) {
                codigoPais = rs.getString(1);
                fecha = rs.getDate(2);
            }
            pk = new TasadolarPk(codigoPais, fecha);//creo el nuevo pk
        } catch (Exception _e) {
            System.out.println("Error al ejecutar la consulta...");
            _e.printStackTrace();
        } finally {//cerrando recursos
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
        }
        return pk;
    }
        
        

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Tasadolar dto)
	{
	}

	/** 
	 * Returns all rows from the tasadolar table that match the specified arbitrary SQL statement
	 */
	public Tasadolar[] findByDynamicSelect(String sql, Object[] sqlParams) throws TasadolarDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			//System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TasadolarDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the tasadolar table that match the specified arbitrary SQL statement
	 */
	public Tasadolar[] findByDynamicWhere(String sql, Object[] sqlParams) throws TasadolarDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TasadolarDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

}
