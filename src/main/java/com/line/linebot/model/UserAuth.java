package com.line.linebot.model;

public class UserAuth {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_AUTH.LINE_USER_ID
     *
     * @mbg.generated
     */
    private String user_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_AUTH.SYSTEM_NAME
     *
     * @mbg.generated
     */
    private String system_name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_AUTH.LINE_USER_ID
     *
     * @return the value of USER_AUTH.LINE_USER_ID
     *
     * @mbg.generated
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_AUTH.LINE_USER_ID
     *
     * @param user_id the value for USER_AUTH.LINE_USER_ID
     *
     * @mbg.generated
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_AUTH.SYSTEM_NAME
     *
     * @return the value of USER_AUTH.SYSTEM_NAME
     *
     * @mbg.generated
     */
    public String getSystem_name() {
        return system_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_AUTH.SYSTEM_NAME
     *
     * @param system_name the value for USER_AUTH.SYSTEM_NAME
     *
     * @mbg.generated
     */
    public void setSystem_name(String system_name) {
        this.system_name = system_name == null ? null : system_name.trim();
    }
}