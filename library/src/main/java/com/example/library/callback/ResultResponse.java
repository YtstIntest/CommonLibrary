package com.example.library.callback;/*
 * Copyright 2016 xialiang(xialiang)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;

/**
 * ================================================
 * 作    者：xialiang（xialiang）Github地址：https://github.com/xialiang
 * 版    本：1.0
 * 创建日期：16/9/28
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public String messageCode;
    public String messageDetail;
    public T data;

    @Override
    public String toString() {
        return "ResultResponse{\n" +//
               "\tcode=" + messageCode + "\n" +//
               "\tmsg='" + messageDetail + "\'\n" +//
               "\tdata=" + data + "\n" +//
               '}';
    }
}
