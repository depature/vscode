package com.atguigu.service.impl;


import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 李文
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-19 19:36:32
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result findNewspage(PortalVo portalVo) {
        IPage<Map> page=new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectMyPage(page,portalVo);
        List<Map> records = page.getRecords();
        Map data=new HashMap();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());
        Map pageInfo=new HashMap();
        pageInfo.put("pageInfo",data);


        return Result.ok(pageInfo);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map headLineDetail = headlineMapper.queryDetailMap(hid);
        //2.拼接头条对象(阅读量和version)进行数据更新
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews")+1); //阅读量+1
        headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);

        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("headline",headLineDetail);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result publish(Headline headline,String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    @Override
    public Result updateData(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result Delete(Integer hid) {
        headlineMapper.deleteById(hid);

        return Result.ok(null);
    }
}




