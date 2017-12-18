package com.bxsurvey.sys.video.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

//import com.bxsurvey.danger.DangerPort.model.DangerPort;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.video.model.SysVideo;
import com.bxsurvey.sys.video.vo.VideoVo;


import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.sys.SysVideoDao.java 
 * Created On: 2015-5-12 下午6:59:08
 * Description: 监控视频dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysVideoDao extends BaseDao<SysVideo, Integer> {
	/*
	 * 删除
	 */
	public void deleteViewById(Integer id) {
		SysVideo video = this.get(id);
		this.delete(video);
	}
	/*
	 * 列表查询
	 */
	public PageResults findListForJson(PageResults page, SysVideo video) {
		Criteria criteria = this.createCriteria(video);
		criteria.add(Restrictions.eq("validity", true));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long)criteria.uniqueResult();
		criteria.setProjection(null);
		List list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
		
		page.setTotalCount(count);
		page.setResult(list);
		return page;
	}
	/**
	 * 
	 * @Title: getByServiceIdAndlayerIdAndFid 
	 * @Description: 根据地图服务id、图层id、要素id查找Video
	 * @param @param mapServiceId
	 * @param @param mapLayerId
	 * @param @param fId
	 * @param @return 
	 * @return Video 
	 * @throws
	 */
	public VideoVo getByServiceIdAndlayerIdAndFid(Integer mapServiceId,
			Integer mapLayerId, String fid) {
		SysVideo videoExp =new SysVideo();
		SysVideo video = this.getUnique(videoExp);
		
		VideoVo videoVo=new VideoVo();
		String url=video.getUrl();
		videoVo.setSrc(url);
		videoVo.setSuffix(url.substring(url.lastIndexOf(".")+1));
		videoVo.setTitle(url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".")));
		return videoVo;
	}
	/** 
	 * @Title: findByDepart 
	 * @Description: 获取企业摄像头列表
	 * @param @param depart
	 * @param @return 
	 * @return SysVideo 
	 * @throws 
	 */
//	public List<SysVideo> findByDepartAndPort(SysDepart depart,DangerPort port) {
//		SysVideo video=new SysVideo();
//		video.setValidity(true);
//		video.setSysDepart(depart);
//		video.setPortId(port.getPortId());
//		return this.findByEntity(video);
//	}
	
	/** 
	 * @Title: findVideoByDepartAndPort 
	 * @Description: 获取企业摄像头列表
	 * @param @param depart
	 * @param @return 
	 * @return SysVideo 
	 * @throws 
	 */
//	public List<SysVideo> findVideoByDepartAndPort(SysDepart depart,DangerPort port) {
//		StringBuilder hql=new StringBuilder();
//		hql.append("SELECT sv ")
//			.append("FROM SysVideo sv ")
//			.append("WHERE sv.validity=true ")
//			.append("AND sv.sysDepart.departId = ? ")
//			.append("AND sv.portId = ? ");
//		return this.find(hql.toString(),depart.getDepartId(), port.getPortId());
//	}
	
	public List<SysVideo> findVediosByPortNameAndDepartName(String departName){
		StringBuilder sb = new StringBuilder();
		sb.append("select sv from SysVideo sv ");
		if(departName!=null && departName!=""){
			sb.append(" where sv.sysDepart.departName = '"+departName+"'");
		}
		List<SysVideo> list = this.find(sb.toString());
		return list;		
	}
	
	
	//搜索查询
	public PageResults getSeachForJson(PageResults page,String name,String departId){
		
		Query query = getSession().createQuery(" from SysVideo as v where v.name like ?");
		List<SysVideo> sysVideos = query.setParameter(0,"%" + name + "%").list();
		List<SysVideo> resu = new ArrayList<SysVideo>();
		int count = 0;
		for(int i = 0;i < sysVideos.size();i++){
			
			Integer depaId = sysVideos.get(i).getSysDepart().getDepartId();
			
			if(null == departId || "".equals(departId)){
							
				resu.add(sysVideos.get(i));
				count ++ ;
			}
			
			if(departId.equals(depaId.toString())){
				resu.add(sysVideos.get(i));
				count ++ ;
			}
			
			
		
		}
		query.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize());
		
		page.setTotalCount(count);
		page.setResult(resu);
		return page;
	}
	public List<SysVideo> findListByXYminOrmax(String minX, String maxX, String minY, String maxY,double r) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("validity", true))
				.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));


		try {
			return pack(Double.parseDouble(minX),Double.parseDouble(minY),Double.parseDouble(maxX),Double.parseDouble(maxY),r,criteria.list());
		} catch (Exception e) {
			throw new RuntimeException("捕捉不到 特定范围内的数据");
		}
	}

}

