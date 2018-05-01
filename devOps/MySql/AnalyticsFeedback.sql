CREATE TABLE `AnalyticsFeedback` (
  `idAnalyticsFeedback` int(11) NOT NULL AUTO_INCREMENT,
  `pmid` varchar(45) DEFAULT NULL,
  `annotationId` varchar(45) DEFAULT NULL,
  `annotationText` varchar(100) DEFAULT NULL,
  `annotationType` varchar(45) DEFAULT NULL,
  `offsetBegin` int(11) DEFAULT NULL,
  `offsetEnd` int(11) DEFAULT NULL,
  `feedback` varchar(10) DEFAULT NULL,
  `timeStamp` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idAnalyticsFeedback`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=big5;
