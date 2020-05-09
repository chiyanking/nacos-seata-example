DROP PROCEDURE IF EXISTS drop_databases;
CREATE PROCEDURE drop_databases(
    IN paramDBName VARCHAR(32)
)
BEGIN
    DECLARE _done INT DEFAULT 0;
    DECLARE _cursorValue VARCHAR(255);

    DECLARE _cursor CURSOR FOR SELECT SCHEMA_NAME FROM information_schema.SCHEMATA
        WHERE SCHEMA_NAME LIKE @_paramDBName;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET _done = 1;

    SET @_paramDBName = CONCAT("%", paramDBName, "%");


    OPEN _cursor;
    REPEAT
        FETCH _cursor INTO _cursorValue;
        IF (_done <> 1) THEN
            SET @prepareStatement = CONCAT("DROP DATABASE `", _cursorValue,"`;");
            SELECT @prepareStatement;
            PREPARE stmt FROM @prepareStatement;
            EXECUTE stmt ;
            DEALLOCATE PREPARE stmt;
        END IF;
    UNTIL _done = 1 end REPEAT;
    CLOSE _cursor;
 END



call drop_databases("seata-order");

