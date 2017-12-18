package com.bxsurvey.sys.depart.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

//import com.bxsurvey.danger.DangerMt.model.DangerMt;
import com.bxsurvey.sys.depart.dao.SysDepartDao;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.depart.service.SysDepartServiceI;

import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;
//import net.framework.httpModel.easyui.Datagrid;
import net.sf.json.JSONObject;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.sys.impl.SysDepartService.java 
 * Created On: 2015-5-5 上午10:13:08
 * Description: 组织机构表业务实现
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("sysDepartService")
public class SysDepartService implements SysDepartServiceI {
	@Resource
	private SysDepartDao departDao;
	/*
	 * 添加
	 */
	public void save(SysDepart depart) {
		//depart.setDepartValidity(1);
		departDao.saveSysDepart(depart);
	}
	/*
	 * 编辑
	 */
	public void update(SysDepart depart) {
		departDao.updateSysDepart(depart);
	}
	/*
	 * 删除
	 */
	public void deleteById(String ids) {
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			departDao.deleteSysDepartById(Integer.parseInt(id));
		}
	}
	/*
	 * 列表查询
	 */
	public PageResults<SysDepart> findListForJson(PageResults<SysDepart> page,
			SysDepart depart) {
		return departDao.getObjListForPage(page, depart);
	}
	/**
	 * (non-Javadoc)
	 * Title: getObjectById
	 * Description:  通过主键获取数据
	 * Created On: 2015-6-17 上午11:10:16
	 * @author ldw 
	 * @param id
	 * @return
	 * @see com.bxsurvey.service.sys.SysDepartServiceI#getObjectById(java.lang.Integer)
	 */
	public SysDepart getObjectById(Integer id) {
		return departDao.get(id);
	}
	
	public List<SysDepart> findAll(Integer departId) {
		return departDao.findAllExit(departId);
	}
	public SysDepart findByFid(String fid) {
		return departDao.findByFid(fid);		
	}
	
//	public  JSONObject getDepartsByPort(List<DangerMt> MtList){
//		List<SysDepart> list = departDao.getDepartsByPort(MtList);
//		Datagrid datagrid = Datagrid.createDatagrid(list);
//		return datagrid.toJSONObject();		
//	}
	/* 
	 * @Description : TODO
	 * @author : czj
	 * @data : 2016-3-22
	 */
	public List<SysDepart> findAll() {
		
		return departDao.findAll();
	}
	@Override
	public List<Tree> getDangerAccountTree(String contextPath) {
		List<Tree> res = new ArrayList<Tree>();
		String departHql ="Select sd.departId,sd.departName,sd.plotGeo from SysDepart sd where sd.departId <>2";
		List departList = departDao.getListMapbyQuery(departHql);
		for (Iterator depart = departList.iterator(); depart.hasNext();) {
			Object[] dTm = (Object[]) depart.next();
        	String departId = dTm[0].toString();
        	String departName = dTm[1].toString();
        	String departPlotGeo = dTm[2]==null?null:dTm[2].toString();
			Tree departTree = new Tree();
			departTree.setName(departName);
			departTree.setId("depart_"+departId);
			departTree.setPid("");
			departTree.setPlotGeo(departPlotGeo);
			departTree.setType("1");
			departTree.setIconOpen(contextPath+"/content/images/ems/1_open.png");
			departTree.setIconClose(contextPath+"/content/images/ems/1_close.png");
			departTree.setIcon(contextPath+"/content/images/ems/1_open.png");
			res.add(departTree);
			String zgqHql ="Select dz.zgqId,dz.name,dz.plotGeo from DangerZgq dz where dz.departId = "+departId;
			List zgqList = departDao.getListMapbyQuery(zgqHql);
			for (Iterator zgq = zgqList.iterator(); zgq.hasNext();) {
				Object[] zgqTm = (Object[]) zgq.next();
	        	String zgqId = zgqTm[0].toString();
	        	String zgqName = zgqTm[1].toString();
	        	String zgqPlotGeo = zgqTm[2]==null?null:zgqTm[2].toString();
	        	Tree zgqTree = new Tree();
	        	zgqTree.setName(zgqName);
	        	zgqTree.setId("zgq_"+zgqId);
	        	zgqTree.setPid("depart_"+departId);
	        	zgqTree.setPlotGeo(zgqPlotGeo);
	        	zgqTree.setType("2");
	        	zgqTree.setIconOpen(contextPath+"/content/images/ems/1_open.png");
	        	zgqTree.setIconClose(contextPath+"/content/images/ems/1_close.png");
	        	zgqTree.setIcon(contextPath+"/content/images/ems/1_open.png");
	        	res.add(zgqTree);
				String zgHql ="Select dz.zgId,dz.name,dz.plotGeo,dz.dangerKnowlage.chName from DangerZg dz where  dz.dangerZgq.zgqId = "+zgqId + " order by dz.name asc";
				List zgList = departDao.getListMapbyQuery(zgHql);
				for (Iterator zg = zgList.iterator(); zg.hasNext();) {
					Object[] zgTm = (Object[]) zg.next();
		        	String zgId = zgTm[0].toString();
		        	String zgName = zgTm[1].toString();
		        	String zgPlotGeo = zgTm[2]==null?null:zgTm[2].toString();
		        	String kName = zgTm[3].toString();
		        	Tree zgTree = new Tree();
		        	zgTree.setName(zgName+"("+kName+")");
		        	zgTree.setId("zg_"+zgId);
		        	zgTree.setPid("zgq_"+zgqId);
		        	zgTree.setPlotGeo(zgPlotGeo);
		        	zgTree.setIcon(contextPath+"/content/images/ems/3.png");
		        	zgTree.setType("3");
					res.add(zgTree);
				}
			}
			String videoHql ="Select sv.id,sv.name,sv.x,sv.y from SysVideo sv where sv.sysDepart.departId = "+departId;
			List videoList = departDao.getListMapbyQuery(videoHql);
			if(videoList.size()  > 0){
				Tree videoFolder = new Tree();
				videoFolder.setName("视频");
				videoFolder.setId("vf_"+departId);
				videoFolder.setPid("depart_"+departId);
				videoFolder.setType("5");
				videoFolder.setPlotGeo(null);
				videoFolder.setIconOpen(contextPath+"/content/images/ems/1_open.png");
	        	videoFolder.setIconClose(contextPath+"/content/images/ems/1_close.png");
				videoFolder.setIcon(contextPath+"/content/images/ems/camer16.png");
				res.add(videoFolder);
				for (Iterator video = videoList.iterator(); video.hasNext();) {
					Object[] videoTm = (Object[]) video.next();
		        	String videoid = videoTm[0].toString();
		        	String videoName = videoTm[1].toString();
		        	String videoX = videoTm[2].toString();
		        	String videoY = videoTm[3].toString();
		        	Tree videoTree = new Tree();
		        	videoTree.setName(videoName);
		        	videoTree.setId("video_"+videoid);
		        	videoTree.setPid("vf_"+departId);
		        	videoTree.setType("4");
		        	videoTree.setPlotGeo("{\"geometry\":{\"x\":"+videoX+",\"y\":"+videoY+",\"spatialReference\":{\"wkid\":3857}},\"symbol\":{\"angle\":0,\"xoffset\":0,\"yoffset\":0,\"type\":\"esriPMS\",\"url\":\""+contextPath+"/content/images/ems/camer.png\",\"width\":24,\"height\":24}}");
		        	videoTree.setIcon(contextPath+"/content/images/ems/camer16.png");
		        	res.add(videoTree);
				}
			}
		}
		return res;
	}
	
}
