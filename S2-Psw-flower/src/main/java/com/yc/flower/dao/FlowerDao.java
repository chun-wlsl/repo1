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
		String sql = "select * from flower  where cid=?";
		return jt.query(sql, flowerRowMapper,cid);
	}
   
   //查找所有商品（花）
   public List<Flower> selectFlower(){
		String sql = "select * from flower where 1=1 limit 0,8";
		return jt.query(sql, flowerRowMapper);
	
   }
   
   //查找折扣最低的商品
   public Flower selectByDiscount() {
	   //select top 6 * from ( select 折扣价/原价 as 折扣 , * from table) a order by 折扣
	   String sql="select * from flower ORDER BY discount LIMIT 0,1";
	   return jt.query(sql, rs->{
			return rs.next() ? flowerRowMapper.mapRow(rs, -1) : null;
		});
   }
	
	//通过商品fid查找鲜花的详细信息(详情）
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
				f.setCid(rs.getInt("cid"));
				f.setFcount(rs.getInt("fcount"));
				f.setAdvice(rs.getString("advice"));
				return f;
			}
		};


		//查找所有的商品
		public List<Flower> selectAllFlower() {
			String sql = "select * from flower where 1=1";
			return jt.query(sql, flowerRowMapper);
		}


		//更新商品信息
		public int update(Flower f) {
			String sql = "update flower set fname = ?,market_price = ?,discount = ?,shop_price = ?,"
					+ "image = ?,cid = ?,is_hot = ?,fcount = ?,advice = ? where fid = ?";
			return jt.update(sql, f.getFname(),f.getMarketPrice(),f.getDiscount(),f.getShopPrice(),
					f.getImage(),f.getCid(),f.getIsHot(),f.getFcount(),f.getAdvice(),f.getAdvice());
		
		}
		
		
		//(前台)更改商品（花)的库存量
		public int updateCount(int fid,int count) {
			String sql="update flower set fcount=fcount- ? where fid=?";
			 return jt.update(sql, count,fid);	
		      
			 
			 
		}
		
		
		
	}

