/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fusion.center.common.constant;

/**
 * 标签定义
 *
 * @author haoxd
 */
public class TraceTagConstant {

    public static final String START_TIME = "start_time";

    /**
     * MySQL TAG
     */
    public static final String SQL_EXEC_BEFORE = "sql_exec_before";

    public static final String SQL_EXEC_AFTER = "sql_exec_after";

    /**
     * 当前节点处理中结束TAG
     */
    public static final String ENDPOINT_DONE = "endpoint_done";


    /**
     * HttpClient拦截器执行标签
     */
    public static final String HTTP_CLIENT_EXEC_BEFORE = "http_client_exec_before";

    public static final String HTTP_CLIENT_EXEC_AFTER = "http_client_exec_after";

    /**
     * OKHTTP Http客户端拦截器执行标签
     */
    public static final String OKHTTP_CLIENT_EXEC_BEFORE = "okhttp_client_exec_before";

    public static final String OKHTTP_CLIENT_EXEC_AFTER = "okhttp_client_exec_after";

    /**
     * RestTemplate 标签
     */
    public static final String REST_TEMPLATE_EXEC_BEFORE = "rest_template_exec_before";

    public static final String REST_TEMPLATE_EXEC_AFTER = "rest_template_exec_after";

    /**
     * Dubbo RPC 调用标签
     */
    public static final String DUBBO_INVOKE_BEFORE = "dubbo_invoke_before";

    public static final String DUBBO_INVOKE_AFTER = "dubbo_invoke_after";


    /**
     * Redis tag.
     */
    public static final String REDIS_EXECUTE_BEFORE = "redis_execute_before";

    public static final String REDIS_EXECUTE_AFTER = "redis_execute_after";
}
