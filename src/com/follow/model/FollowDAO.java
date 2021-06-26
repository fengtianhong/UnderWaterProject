package com.follow.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class FollowDAO implements FollowDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    private static final String INSER_STMT = "INSERT INTO Follow(Follower,Followed) VALUES(?,?)";
    private static final String DELETE_STMT = "DELETE FROM Follow where Follower = ? and Followed = ?";
    private static final String GET_FOLLOWME_STMT = "SELECT * FROM FOLLOW WHERE Follower = ?";
    private static final String GET_IFOLLOW_STMT = "SELECT * FROM FOLLOW WHERE Followed = ?";

	@Override
	public void insert(Integer follower,Integer followed) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(INSER_STMT);
            ps.setInt(1, follower);
            ps.setInt(2, followed);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	@Override
    public void delete(Integer follower, Integer followed) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, follower);
            ps.setInt(2, followed);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public List<Integer> findFollower(Integer follower) {
        List<Integer> list = new ArrayList<Integer>();
        FollowVO followVO = null;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con=ds.getConnection();
            ps = con.prepareStatement(GET_FOLLOWME_STMT);
            ps.setInt(1, follower);
            rs= ps.executeQuery();
            
            while(rs.next()) {

                list.add(rs.getInt("Followed"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        
        return list;
	}

	@Override
	public List<Integer> findFollowed(Integer followed) {
        List<Integer> list = new ArrayList<Integer>();
        FollowVO followVO = null;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con=ds.getConnection();
            ps = con.prepareStatement(GET_IFOLLOW_STMT);            
            ps.setInt(1, followed);
            rs= ps.executeQuery();
            
            while(rs.next()) {
                list.add(rs.getInt("Follower"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
	}

}
