package com.rison.rpc.util

import java.io.{InputStream, InputStreamReader}
import java.nio.charset.StandardCharsets
import java.util.Properties

/**
 * @author : Rison 2021/7/21 下午5:27
 *         配置文件工具类
 */
object PropertiesUtil {
  /**
   * 加载prop
   *
   * @param propertiesName
   * @return
   */
  def load(propertiesName: String) = {
    val prop: Properties = new Properties()
    //加载指定的配置文件
    prop.load(
      new InputStreamReader(
        Thread.currentThread().getContextClassLoader.getResourceAsStream(propertiesName),
        StandardCharsets.UTF_8
      )
    )
    prop
  }

  val baseUrl = load("application.properties").getProperty("api.base.url")
  val appId = load("application.properties").getProperty("api.app.id")
  val accessKeyId = load("application.properties").getProperty("api.access.key.id")
  val signature = load("application.properties").getProperty("api.signature")
  val signatureMethod = load("application.properties").getProperty("api.signature.method")
  val signatureVersion = load("application.properties").getProperty("api.signature.version")
  val signatureNonce = load("application.properties").getProperty("api.signature.nonce")
  val timestamp = load("application.properties").getProperty("api.timestamp")
  val version = load("application.properties").getProperty("api.version")
  val format = load("application.properties").getProperty("api.format")

  val appIdName = load("application.properties").getProperty("api.app.id.name")
  val accessKeyIdName = load("application.properties").getProperty("api.access.key.id.name")
  val signatureName = load("application.properties").getProperty("api.signature.name")
  val signatureMethodName = load("application.properties").getProperty("api.signature.method.name")
  val signatureVersionName = load("application.properties").getProperty("api.signature.version.name")
  val signatureNonceName = load("application.properties").getProperty("api.signature.nonce.name")
  val timestampName = load("application.properties").getProperty("api.timestamp.name")
  val versionName = load("application.properties").getProperty("api.version.name")
  val formatName = load("application.properties").getProperty("api.format.name")


  val app = load("application.properties").getProperty("api.type.app")
  val robot = load("application.properties").getProperty("api.type.robot")
  val file = load("application.properties").getProperty("api.type.file")
  val task = load("application.properties").getProperty("api.type.task")

//  val APP_API






}
