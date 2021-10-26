-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: gras
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gitrepository`
--

DROP TABLE IF EXISTS `gitrepository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gitrepository` (
  `id` varchar(45) DEFAULT NULL,
  `reponame` varchar(45) DEFAULT NULL,
  `ownername` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gitrepository`
--

LOCK TABLES `gitrepository` WRITE;
/*!40000 ALTER TABLE `gitrepository` DISABLE KEYS */;
INSERT INTO `gitrepository` VALUES ('6b376ef6-76ee-4ca6-89ff-1ec7b1c2bfb5','gphotos-python-download-REST','KEEEER'),('845735d5-7e87-4ad0-98b8-7b94f3fd26b3','GitRepositoryAnalysisSystem','KEEEER'),('2e723743-f67c-4a6a-9c12-ba3c831b6883','gphotos-python-download-REST','KEEEER'),('275147e4-992b-4e2f-8168-72c1754265b8','gphotos-python-download-REST','KEEEER'),('4517f238-4826-4d28-95d4-c33acaf11377','gphotos-python-download-REST','KEEEER'),('f18b8ed7-bec2-4cc0-8b62-9e2526a8754b','gphotos-python-download-REST','KEEEER'),('fd50f3bb-14a0-483e-8ee0-564ac1b9a8cb','GitRepositoryAnalysisSystem','KEEEER'),('5b5f8d50-60bf-4f35-9e02-19ac62f6d8e4','gphotos-python-download-REST','KEEEER'),('8e07c46c-378c-481d-84e2-23758015b1e6','angular','angular'),('59a764d5-3128-4516-9b62-954c6668b2b7','examples','tensorflow'),('47d3f85e-1424-4f6b-8c26-d1486bc79b03','go','golang');
/*!40000 ALTER TABLE `gitrepository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `repoid` varchar(500) DEFAULT NULL,
  `starttime` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('32fc7f0d-65f7-45aa-a816-676e4c2416cb','<script>alert(\"meow1)!\")</script>','<script>alert(\"meow1)!\")</script>','',NULL),('0f7baf6b-cb1d-4851-a9b9-c6336bd127a7','<script>alert(\"meow!\")</script>','<script>alert(\"meow!\")</script>','',NULL),('4061b41e-fd42-4434-92fb-5a63047ba22d','123','123','',NULL),('4061b41e-fd42-4434-92fb-5a63047ba22d','123','123','fd50f3bb-14a0-483e-8ee0-564ac1b9a8cb',NULL),('4061b41e-fd42-4434-92fb-5a63047ba22d','123','123','5b5f8d50-60bf-4f35-9e02-19ac62f6d8e4',NULL),('3a7b345c-70d7-4918-b4eb-4ac626593f14','456','789','',NULL),('3a7b345c-70d7-4918-b4eb-4ac626593f14','456','789','8e07c46c-378c-481d-84e2-23758015b1e6',NULL),('3a7b345c-70d7-4918-b4eb-4ac626593f14','456','789','47d3f85e-1424-4f6b-8c26-d1486bc79b03',NULL),('3a7b345c-70d7-4918-b4eb-4ac626593f14','456','789','59a764d5-3128-4516-9b62-954c6668b2b7',NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `account` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1a342ff6-83a5-4f21-b95f-969aa9b00050','fish han','bigMoney','bbb'),('b83c00df-5c65-4e82-a550-6b35cfe5e85b','fish han','bigMoney','bbb'),('1fbf3bd9-b418-4a1a-af61-1651c73e0dbf','fish han','bigMoney','bbb'),('dea8dbe0-8c45-47ba-a9da-6af10e136864','<script>alert(\"meow!\")</script>','ff@gmail.com','123'),('4d0f5ebc-a68f-4581-9527-406176dc581e','123','123@gmail.com','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_project`
--

DROP TABLE IF EXISTS `user_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_project` (
  `userid` varchar(300) DEFAULT NULL,
  `projectid` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_project`
--

LOCK TABLES `user_project` WRITE;
/*!40000 ALTER TABLE `user_project` DISABLE KEYS */;
INSERT INTO `user_project` VALUES ('dea8dbe0-8c45-47ba-a9da-6af10e136864','0f7baf6b-cb1d-4851-a9b9-c6336bd127a7'),('4d0f5ebc-a68f-4581-9527-406176dc581e','4061b41e-fd42-4434-92fb-5a63047ba22d'),('4d0f5ebc-a68f-4581-9527-406176dc581e','3a7b345c-70d7-4918-b4eb-4ac626593f14');
/*!40000 ALTER TABLE `user_project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-07 17:04:17
