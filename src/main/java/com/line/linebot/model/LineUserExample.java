package com.line.linebot.model;

import java.util.ArrayList;
import java.util.List;

public class LineUserExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public LineUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenIsNull() {
            addCriterion("LINE_TOKEN is null");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenIsNotNull() {
            addCriterion("LINE_TOKEN is not null");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenEqualTo(String value) {
            addCriterion("LINE_TOKEN =", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenNotEqualTo(String value) {
            addCriterion("LINE_TOKEN <>", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenGreaterThan(String value) {
            addCriterion("LINE_TOKEN >", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenGreaterThanOrEqualTo(String value) {
            addCriterion("LINE_TOKEN >=", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenLessThan(String value) {
            addCriterion("LINE_TOKEN <", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenLessThanOrEqualTo(String value) {
            addCriterion("LINE_TOKEN <=", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenLike(String value) {
            addCriterion("LINE_TOKEN like", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenNotLike(String value) {
            addCriterion("LINE_TOKEN not like", value, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenIn(List<String> values) {
            addCriterion("LINE_TOKEN in", values, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenNotIn(List<String> values) {
            addCriterion("LINE_TOKEN not in", values, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenBetween(String value1, String value2) {
            addCriterion("LINE_TOKEN between", value1, value2, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andUser_line_tokenNotBetween(String value1, String value2) {
            addCriterion("LINE_TOKEN not between", value1, value2, "user_line_token");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDept_codeIsNull() {
            addCriterion("DEPT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDept_codeIsNotNull() {
            addCriterion("DEPT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDept_codeEqualTo(String value) {
            addCriterion("DEPT_CODE =", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotEqualTo(String value) {
            addCriterion("DEPT_CODE <>", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeGreaterThan(String value) {
            addCriterion("DEPT_CODE >", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_CODE >=", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeLessThan(String value) {
            addCriterion("DEPT_CODE <", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeLessThanOrEqualTo(String value) {
            addCriterion("DEPT_CODE <=", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeLike(String value) {
            addCriterion("DEPT_CODE like", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotLike(String value) {
            addCriterion("DEPT_CODE not like", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeIn(List<String> values) {
            addCriterion("DEPT_CODE in", values, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotIn(List<String> values) {
            addCriterion("DEPT_CODE not in", values, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeBetween(String value1, String value2) {
            addCriterion("DEPT_CODE between", value1, value2, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotBetween(String value1, String value2) {
            addCriterion("DEPT_CODE not between", value1, value2, "dept_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeIsNull() {
            addCriterion("TEAM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTeam_codeIsNotNull() {
            addCriterion("TEAM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTeam_codeEqualTo(String value) {
            addCriterion("TEAM_CODE =", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeNotEqualTo(String value) {
            addCriterion("TEAM_CODE <>", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeGreaterThan(String value) {
            addCriterion("TEAM_CODE >", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeGreaterThanOrEqualTo(String value) {
            addCriterion("TEAM_CODE >=", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeLessThan(String value) {
            addCriterion("TEAM_CODE <", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeLessThanOrEqualTo(String value) {
            addCriterion("TEAM_CODE <=", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeLike(String value) {
            addCriterion("TEAM_CODE like", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeNotLike(String value) {
            addCriterion("TEAM_CODE not like", value, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeIn(List<String> values) {
            addCriterion("TEAM_CODE in", values, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeNotIn(List<String> values) {
            addCriterion("TEAM_CODE not in", values, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeBetween(String value1, String value2) {
            addCriterion("TEAM_CODE between", value1, value2, "team_code");
            return (Criteria) this;
        }

        public Criteria andTeam_codeNotBetween(String value1, String value2) {
            addCriterion("TEAM_CODE not between", value1, value2, "team_code");
            return (Criteria) this;
        }

        public Criteria andUser_statusIsNull() {
            addCriterion("USER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andUser_statusIsNotNull() {
            addCriterion("USER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andUser_statusEqualTo(String value) {
            addCriterion("USER_STATUS =", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusNotEqualTo(String value) {
            addCriterion("USER_STATUS <>", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusGreaterThan(String value) {
            addCriterion("USER_STATUS >", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusGreaterThanOrEqualTo(String value) {
            addCriterion("USER_STATUS >=", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusLessThan(String value) {
            addCriterion("USER_STATUS <", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusLessThanOrEqualTo(String value) {
            addCriterion("USER_STATUS <=", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusLike(String value) {
            addCriterion("USER_STATUS like", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusNotLike(String value) {
            addCriterion("USER_STATUS not like", value, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusIn(List<String> values) {
            addCriterion("USER_STATUS in", values, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusNotIn(List<String> values) {
            addCriterion("USER_STATUS not in", values, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusBetween(String value1, String value2) {
            addCriterion("USER_STATUS between", value1, value2, "user_status");
            return (Criteria) this;
        }

        public Criteria andUser_statusNotBetween(String value1, String value2) {
            addCriterion("USER_STATUS not between", value1, value2, "user_status");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LINE_USER
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LINE_USER
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}