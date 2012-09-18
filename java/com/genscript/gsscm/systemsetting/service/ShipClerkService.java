/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.systemsetting.service;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.shipment.dao.ShipClerkAssignDao;
import com.genscript.gsscm.shipment.dao.ShipClerkDao;
import com.genscript.gsscm.shipment.entity.ShipClerk;
import com.genscript.gsscm.shipment.entity.ShipClerkAssigned;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jinsite
 */
@Service
@Transactional
public class ShipClerkService {
    @Autowired
    private ShipClerkDao shipClerkDao;
    @Autowired
    private ProductClassDao productClassDao;
    @Autowired
    private ServiceClassDao serviceClassDao;
    @Autowired
    private ShipClerkAssignDao shipClerkAssignDao;
    @Autowired
    private UserDao userDao ;

    /**
     * Searh ShipClerk
     * @param page
     * @param filters
     * @return
     */
   @Transactional(readOnly=true)
   public Page<ShipClerk> searchShipClerk(Page<ShipClerk> page, List<PropertyFilter> filters){
            return shipClerkDao.findPage(page, filters);
     }

   /**
    * Searh ShipClerk
    * @param page
    * @param filterParaMap
    * @return
    */
    @Transactional(readOnly=true)
    public Page<ShipClerk> searchShipClerk(Page<ShipClerk> page, Map<String,String> filterParaMap){
            List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
            for(Map.Entry<String,String> entry: filterParaMap.entrySet()){
                String filterName=entry.getKey();
                String filterValue=entry.getValue();
                boolean omit=StringUtils.isNotBlank(filterValue);
                if(!omit){
                    PropertyFilter filter=new PropertyFilter(filterName, filterValue);
                    filters.add(filter);
                }
            }
            return shipClerkDao.findPage(page,filters );
        }

    /**
     * Searh ShipClerk
     * @param page
     * @return
     */
    @Transactional(readOnly=true)
    public Page<ShipClerk> searchShipClerk(Page<ShipClerk> page){
            return shipClerkDao.findPage(page);
    }

    /**
     * Searh ShipClerk
     * @param page
     * @param hql
     * @param values
     * @return
     */
    @Transactional(readOnly=true)
    public Page<ShipClerk> searchShipClerk(Page<ShipClerk> page,String hql, final Object... values){
        return shipClerkDao.findPage(page, hql, values);
     //   return shipClerkDao.findClerkPage(page, hql, values);
    }
    /**
     * Searh ShipClerk
     * @param page
     * @param hql
     * @param values
     * @return
     */
   // @Transactional(readOnly=true)
    public Page<ShipClerk> searchShipClerk(Page<ShipClerk> page,String hql, Map<String,Object> values){
       return shipClerkDao.findPage(page, hql, values);
    }
    /**
     * 
     * @param page
     * @param filters
     * @param values
     * @return
     */
    public Page<ShipClerk> searchShipClerk(Page<ShipClerk> page,List<PropertyFilter> filters, String[] values){
        String where="";
        for(PropertyFilter filter: filters){
            where+=" and clerk."+filter.getPropertyName()+"= '"+ filter.getPropertyValue().toString()+"'";
        }
        where+=" and assigned.itemType='"+ values[3]+"'";
        where+=" and assigned.clsId="+ values[2];
       return searchShipClerk(page,"select clerk from ShipClerk clerk, ShipClerkAssigned assigned where clerk.id=assigned.shippingClerk "+where);
       // return null;
    }
    /**
     * Save Clerk
     * @param shipClerk
     */
    public void saveShipClerk(ShipClerk shipClerk){
            shipClerkDao.save(shipClerk);
        }

    /**
     * Get All Shipping Clerks
     * @return
     */
    @Transactional(readOnly=true)
    public List<ShipClerk> getAllClerks(){
        return shipClerkDao.getAll();
    }

    /**
     * Get ShipClerk By clerkId
     * @param clerkId
     * @return
     */
    public ShipClerk getShipClerk(int clerkId){
        return shipClerkDao.get(clerkId);
    }

   
    /**
     * According to Shipping Clerk Id gets the Shipping Clerk Assigned data
     * @param clerk_id
     * @return
     */
    @Transactional(readOnly=true)
    public List<ShipClerkAssigned> getShipClerkAssignedByClerkId(Integer clerk_id){
        List<ShipClerkAssigned> list = shipClerkAssignDao.findBy("shippingClerk", clerk_id);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getItemType().equalsIgnoreCase("SERVICE")){
                    list.get(i).setClsName(serviceClassDao.get(list.get(i).getClsId()).getName());
             }
            if(list.get(i).getItemType().equalsIgnoreCase("PRODUCT")){
                 list.get(i).setClsName(productClassDao.get(list.get(i).getClsId()).getName());
             }
         }

        return list;
    }
    
    public User getUser(Integer clerkId){
    	return userDao.getById(clerkId);
    }
    public String getEmployeeName(int clerkId){
    	User user = userDao.getById(clerkId);
    	if(user!=null&&user.getEmployee()!=null){
    		return user.getEmployee().getEmployeeName();
    	}else{
    		return "";
    	}
        
    }
    @Transactional(readOnly=true)
    public String getNames(Integer clerk_id){
        List<ShipClerkAssigned> list=getShipClerkAssignedByClerkId(clerk_id);
        String names="";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getItemType().equals("SERVICE")){
                  names+="Service "+serviceClassDao.get(list.get(i).getClsId()).getName()+",";
                }
            if(list.get(i).getItemType().equals("PRODUCT")){
                names+= "Product " +productClassDao.get(list.get(i).getClsId()).getName()+",";
            }
        }

        //names.substring(clerk_id, clerk_id)
        return names.length()>0?names.substring(0, names.length()-1):"";
    }
    /**
     * Save ShipClerkAssigned
     * @param clerkId
     * @param list
     */
   public void saveAssigned(Integer clerkId,List<ShipClerkAssigned> list){
       for(ShipClerkAssigned assigned: list){
           assigned.setShippingClerk(clerkId);
           shipClerkAssignDao.save(assigned);
       }

   }
   /**
    * Delete Assigned Product/Service
    * @param list
    */
   public void delAssigned(List<Integer> list){
       for(int assignedId:list){
        shipClerkAssignDao.delete(assignedId);
       }
   }
   /**
    * Delete Assigned Product/Service
    * @param assignedList
    */
   public void delShipClerkAssigned(List<ShipClerkAssigned> assignedList){
        for(ShipClerkAssigned assigned: assignedList){
            shipClerkAssignDao.delete(assigned);
        }
   }
   /**
    * Delete Ship Clerk
    * @param clerkId
    */
   public void delShipClerk(Integer clerkId){
       shipClerkDao.delete(clerkId);
   }
   /**
    * Delete Ship Clerk
    * @param list
    */
   public void delShipClerk(List<Integer> list){
       for(Integer clerkId: list){
           delShipClerk(clerkId);
       }
   }
   /**
    * Get Product and Service names and ids.
    * @return
    */
    public Map<String,String> getProductAndService(){
        List<ProductClass> productcls = productClassDao.findAll(Page.ASC, "name");
        List<ServiceClass> servicecls = serviceClassDao.findAll(Page.ASC, "name");
        //Map<String,String> allcls=new HashMap<String, String>();
        TreeMap<String, String> allcls = new TreeMap<String, String>();

        for(int i=0;i<productcls.size();i++){
            allcls.put("P:"+productcls.get(i).getName()+":"+productcls.get(i).getClsId()+":PRODUCT", "Product - "+productcls.get(i).getName());
        }
        for(int i=0;i<servicecls.size();i++){
            allcls.put("S:"+servicecls.get(i).getName()+":"+servicecls.get(i).getClsId()+":SERVICE", "Service - "+servicecls.get(i).getName());
        }
        return allcls;
    }

    
    /*
     * 根据 type and clsid 查询shipping_clerk_assigned
     */
    public List<ShipClerkAssigned> getShipClerkAssignedList (String itemType,Integer clsId){
    	return this.shipClerkAssignDao.getShipClerkAssingByItemTypeAndClsId(itemType, clsId);
    }
    public List<ShipClerkAssigned> getShipClerkAssignedList2(){
    	return this.shipClerkAssignDao.getShipClerkAssing();
    }
}
