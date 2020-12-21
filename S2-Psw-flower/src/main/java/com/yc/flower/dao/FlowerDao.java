package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.Flower;



@Repository
public class FlowerDao extends BaseDao{
	
	//查找最热商品（花）
   public List<Flower> selectHot(){
		String sql = "select * from flower where is_hot=1 limit 0,10";
		return jt.query(sql, flowerRowMapper);
	}
	
   //查找类别为cid的所有商品（花）
   public List<Flower> queryFlowerByCid(int cid) {
		String sql = "select * from flower f where cid=?";
		return jt.query(sql, flowerRowMapper,cid);
	}
	
	//通过商品fid查找鲜花的详细信息
	public Flower queryFlowerById(int fid) {
		String sql = "select * from flower where fid=?";
		return jt.query(sql, rs->{
			return rs.next() ? flowerRowMapper.mapRow(rs, -1) : null;
		}, fid);
		
	}
	
	
	//新增商品
	public void insert(Flower f) {
		String sql = "insert into flower values(null,?,?,?,?,?,?,?,now(),?,?)";
		jt.update(sql,
		f.getFname(),
		f.getMarketPrice(),
		f.getDiscount(),
		f.getShopPrice(),
		f.getImage(),
		f.getCid(),
		f.getIsHot(),
		f.getFcount(),
		f.getAdvice()
		);
		
	}
	
		
		 RowMapper<Flower> flowerRowMapper = new RowMapper<Flower>() {

			@Override
			public Flower mapRow(ResultSet rs, int rowNum) throws SQLException {
				Flower f = new Flower();
				f.setFid(rs.getInt("fid"));
				f.setFname(rs.getString("fname"));
				
				f.setMarketPrice(rs.getDouble("market_price"));
				f.setDiscount(rs.getDouble("discount"));
				f.setShopPrice(rs.getDouble("shop_price"));
				f.setImage(rs.getString("image"));
				
				f.setIsHot(rs.getInt("is_hot"));
				f.setFdate(rs.getDate("fdate"));
				f.setCid(rs.getInt("csid"));
				f.setAdvice(rs.getString("advice"));
				return f;
			}
		};
		
		
		
		
		
		
	}

