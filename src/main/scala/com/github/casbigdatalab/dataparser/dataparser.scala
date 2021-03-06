package com.github.casbigdatalab.datachain.dataparser

/**
 * Created by cnic-liliang on 2016/6/3.
 */

import java.util

object parserCreater {
  var mapping_type = "csvMapping"

  def creater(mapping_json: String): common = {
    val mapping = tools.jsonfile2JsonMap(mapping_json)

    val map: Map[String, Any] = mapping.asInstanceOf[Map[String, Any]].get("mappingSpec").get.asInstanceOf[Map[String, Any]]
    mapping_type = map.get("type").get.asInstanceOf[String]

    val parser =
      mapping_type match {
        case "csvMapping" => new csvdataparser(mapping_json)
        case "jsonMapping" => new jsondataparser(mapping_json)
      }
    parser
  }
}

class dataparser(mapping_json: String, parser: common) {
  def this(mapping: String) = {
    this(mapping, parserCreater.creater(mapping))
  }

  def parse(msg: String): util.ArrayList[Map[String, Any]] = {
    parser.parse(msg)
  }
}
