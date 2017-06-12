/**
 * Created by Daniel on 2017/3/27.
 * MVC中的控制器，接受前端发来的请求
 * 以REST Controller实现，主要为了直接返回json数据
 * 其中以/api/auth开头的路由需要经过过滤器对token进行解析处理，对应的HttpRequest里会保存用户token
 */
package top.yyf.controller;