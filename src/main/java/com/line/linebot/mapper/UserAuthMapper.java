package com.line.linebot.mapper;

import com.line.linebot.model.UserAuth;
import com.line.linebot.model.UserAuthExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface UserAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    long countByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    int deleteByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    int insert(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    int insertSelective(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    List<UserAuth> selectByExampleWithRowbounds(UserAuthExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    List<UserAuth> selectByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_AUTH
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") UserAuth record, @Param("example") UserAuthExample example);
}