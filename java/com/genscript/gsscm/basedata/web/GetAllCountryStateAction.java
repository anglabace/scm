package com.genscript.gsscm.basedata.web;

import com.genscript.core.orm.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.AllStateDTO;
import com.genscript.gsscm.basedata.dto.ZipCodeDTO;
import com.genscript.gsscm.basedata.entity.ZipCode;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;

/**
 * 获得country 列表。
 * @author zouyulu
 *
 */
public class GetAllCountryStateAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1341161883764740845L;
	
	@Autowired
	private PublicService publicService;

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		List<AllCountryDTO> countryStateList = publicService.getAllCountryState();
		String countryStr = "";
		String stateStr = "";
		int count = 0;
		int count2 = 0;
		for(AllCountryDTO countryDTO : countryStateList){
			count++;
			if(count == 1){
				countryStr = countryDTO.getCountryCode()+":"+countryDTO.getName();
			}else{
				countryStr += "|"+countryDTO.getCountryCode()+":"+countryDTO.getName();
			}
			List<AllStateDTO> allStateDTO = countryDTO.getAllStateDTOs();
			if(allStateDTO != null){
				for(AllStateDTO stateDTO: allStateDTO){
					count2++;
					if (count2 == 1){
						stateStr = countryDTO.getCountryCode()+":"+stateDTO.getStateCode()+":"+stateDTO.getName();
					}else {
						stateStr += "|"+countryDTO.getCountryCode()+":"+stateDTO.getStateCode()+":"+stateDTO.getName();
					}
				}
			}
		}
		Map<String, String> rs = new HashMap<String, String>();
		rs.put("countryStr", countryStr);
		rs.put("stateStr", stateStr);
		Struts2Util.renderJson(rs);
		return null;
	}
      
        public String getZipCodeList() throws Exception
         {
            Page<ZipCode> page=new Page<ZipCode>();
            page.setPageSize(20);
            String  countryCodeStr=ServletActionContext.getRequest().getParameter("countryCodeStr");
            String stateCodeStr=ServletActionContext.getRequest().getParameter("stateCodeStr");
            String pageNo=ServletActionContext.getRequest().getParameter("pageNo");
            int pages=0;
            if(pageNo==null||pageNo.isEmpty())
            {
                page.setPageNo(1);
            }
             else{
                page.setPageNo(Integer.parseInt(pageNo));
                pages=Integer.parseInt(pageNo);
             }
             pages=pages>0?pages:0;
            List<String> countryList=new ArrayList<String>();
            List<String> stateList=new ArrayList<String>();
            String[] country=countryCodeStr.split(",");
            String[] state=stateCodeStr.split(",");
            
            String countryStr="";
            String stateStr="";
            for(int i=0;i<country.length;i++)
            {
                countryStr+=country[i]+",";
                countryList.add(country[i]);
            }
            countryStr=countryStr.substring(0,countryStr.length()-1);
             for(int i=0;i<state.length;i++)
            {
                 stateStr+=state[i]+",";
                stateList.add(state[i]);
            }
              stateStr=  stateStr.substring(0,   stateStr.length()-1);

           Page<ZipCodeDTO> pageZip= publicService.getZipCodeList(page, countryList, stateList);
           String zipCodeStr="";
           if(pageZip!=null)
           {
              // pageZip.setOrder("id");
               // pageZip.setOrderBy(String.valueOf(pageZip.DESC));
               zipCodeStr+="{\"hasException\":false,\"pagerDTO\":{\"autoCount\":true,\"order\":\"desc\",\"orderBy\":\"id\",\"pageNo\":"+pageZip.getPageNo()+",\"pageSize\":"+pageZip.getPageSize()+",\"totalCount\":"+pageZip.getTotalCount()+"},";
               if (pageZip.getResult() != null && pageZip.getResult().size() > 0) {
                   zipCodeStr += "\"zipCodeList\":[";
                   for (int i = 0; i < pageZip.getResult().size(); i++) {
                       zipCodeStr += "{\"city\":\"" + pageZip.getResult().get(i).getCity() + "\",\"country\":\"" + pageZip.getResult().get(i).getCountry() + "\",\"countryName\":\"" + pageZip.getResult().get(i).getCountryName() + "\",\"id\":" + pageZip.getResult().get(i).getId() + ",\"state\":\"" + pageZip.getResult().get(i).getState() + "\",\"stateName\":\"" + pageZip.getResult().get(i).getStateName() + "\",\"zipCode\":" + pageZip.getResult().get(i).getZipCode() + "},";
                   }
                   zipCodeStr = zipCodeStr.substring(0, zipCodeStr.length() - 1) + "],";
               }
               zipCodeStr+="\"pager\":";
               System.out.println("========================TotalPage:"+pageZip.getTotalPage());
               long maxPage=0l;
               int minPage=0;
               if(pages+5<pageZip.getTotalPage())
               {
                      maxPage=((long)pages +5);
               }
                 else{
                    maxPage =pageZip.getTotalPage();
                 }
               if(pages-5>1)
               {
                   minPage=pages-5;
               }
                 else{
                    minPage=1;
                    maxPage=pageZip.getTotalPage()>10?10:pageZip.getTotalPage();
                 }
               if(minPage==1)
                     zipCodeStr+="\"<span class=\\\"disabled\\\"><<\\/span>";
               else
                    zipCodeStr+="\"<a  href=\\\"javascript:initZipTable('\\/scm\\/util\\/get_all_country_state!getZipCodeList.action?countryCodeStr="+countryStr+"&stateCodeStr="+stateStr+"&ajax=initZipTable&pageNo="+(pageZip.getPageNo()-1)+"')\\\">><\\/a>";
               for(int j=minPage;j<=maxPage;j++)
               {
                   
                   if (pageZip.getPageNo().equals(j)) {
                       zipCodeStr+="<span class=\\\"current\\\">"+j+"<\\/span>\\n";
                   } else {
                      zipCodeStr+= "<a  href=\\\"javascript:initZipTable('\\/scm\\/util\\/get_all_country_state!getZipCodeList.action?countryCodeStr="+countryStr+"&stateCodeStr="+stateStr+"&ajax=initZipTable&pageNo="+j+"')\\\">"+j+"<\\/a>\\n";
                   }
               }
               if(pageZip.getTotalPage().equals(maxPage))
                    zipCodeStr+="<span class=\\\"disabled\\\">><\\/span>";
                   else
                zipCodeStr+= "<a  href=\\\"javascript:initZipTable('\\/scm\\/util\\/get_all_country_state!getZipCodeList.action?countryCodeStr="+countryStr+"&stateCodeStr="+stateStr+"&ajax=initZipTable&pageNo="+(pageZip.getPageNo()+1)+"')\\\">><\\/a>";
               zipCodeStr+="\"}";
           }
           Struts2Util.renderJson(zipCodeStr);
            return null;
        }
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
