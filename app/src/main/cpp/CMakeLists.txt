cmake_minimum_required(VERSION 3.18.1)

project("mshotfix")

aux_source_directory(bzip2 B_SOURCE)  #批量导入源文件
include_directories(bzip2)  #指定头文件的查找目录

add_library(
        mshotfix
        SHARED
        native-lib.cpp
        bspatch.c
        ${B_SOURCE}
)


find_library(
        log-lib
        log)


target_link_libraries(
        mshotfix
        ${log-lib})