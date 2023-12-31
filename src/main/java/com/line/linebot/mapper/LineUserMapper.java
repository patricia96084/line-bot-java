package com.line.linebot.mapper;

import com.line.linebot.model.LineUser;
import com.line.linebot.model.LineUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface LineUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    long countByExample(LineUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int deleteByExample(LineUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int insert(LineUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int insertSelective(LineUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    List<LineUser> selectByExampleWithRowbounds(LineUserExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    List<LineUser> selectByExample(LineUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    LineUser selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LineUser record, @Param("example") LineUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LineUser record, @Param("example") LineUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LineUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LineUser record);
}