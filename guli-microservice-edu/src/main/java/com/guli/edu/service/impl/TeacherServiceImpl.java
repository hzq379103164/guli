package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.com.guli.edu.query.TeacherQuery;
import com.guli.edu.entity.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-07-12
 */
@Service
public  class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort").orderByDesc("id");


        if (teacherQuery == null){//如果没有执行查询，
            baseMapper.selectPage(pageParam, queryWrapper);//那么直接分页排序
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {//如果name不为空
            //注意使用like会影响查询性能，建议在前端使用auto complete
            queryWrapper.like("name", name);//进行查询
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);//ge大于等于
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);//le小于等于
        }

        baseMapper.selectPage(pageParam, queryWrapper);//分页查询结果封装在
    }
}