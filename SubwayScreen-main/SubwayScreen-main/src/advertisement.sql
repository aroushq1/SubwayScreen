-- Drop the existing database if it exists
DROP DATABASE IF EXISTS advertisement;

-- Create a new database
CREATE DATABASE advertisement; 

-- Select the new database
USE advertisement;

-- Drop the existing table if it exists
DROP TABLE IF EXISTS advertisements;

-- Create the table with the correct columns
CREATE TABLE advertisements (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(100),
  media_type VARCHAR(10),
  filepath VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

-- Insert data into the table
INSERT INTO advertisements (title, media_type, filepath) VALUES ('Sprite Ad', 'JPEG', 'SubwayScreen-main/ads/sprite.jpg');
INSERT INTO advertisements (title, media_type, filepath) VALUES ('Coca-Cola Ad', 'JPEG', 'SubwayScreen-main/ads/cokeAd.jpg');
INSERT INTO advertisements (title, media_type, filepath) VALUES ('Subway Ad', 'JPEG', 'SubwayScreen-main/ads/subwayAd.jpg');






