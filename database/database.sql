create database WebBanSach
use WebBanSach

INSERT INTO [dbo].[user] ([delivery_address] ,[email] ,[first_name] ,[gender]  ,[last_name] ,[password] ,[phone] ,[purchase_address] ,[username])
VALUES (N'60 Hoàng Hữu Nam, Quận 9, TP.HCM' ,'admin@email.com', N'Phùng Hữu' ,'M' , N'Thành' ,'12345' ,'0707061211' , N'141 Trần Phú, TP. Bảo Lộc' ,'phuuthanh2003'),
('456 Elm St, Villagetown, USA', 'user2@email.com', 'Bob', 'M', 'Johnson', 'password456', '234-567-8901', '789 Maple Ln, Hamletville, USA', 'bob_johnson'),
('789 Oak St, Townsville, USA', 'user3@email.com', 'Carol', 'F', 'Williams', 'password789', '345-678-9012', '123 Pine Rd, Citytown, USA', 'carol_williams'),
('987 Cedar St, Hamletville, USA', 'user4@email.com', 'David', 'M', 'Brown', 'passwordabc', '456-789-0123', '987 Elm Ave, Villagetown, USA', 'david_brown'),
('654 Pine St, Citytown, USA', 'user5@email.com', 'Emma', 'F', 'Davis', 'passwordxyz', '567-890-1234', '654 Cedar Rd, Hamletville, USA', 'emma_davis');

INSERT INTO [dbo].[role] ([role_name]) 
VALUES ('Admin'),
	   ('Staff'),
	   ('User');

INSERT INTO [dbo].[user_role] ([role_id], [user_id])
VALUES (1, 1),
	   (2, 2),
	   (3, 3),
	   (3, 4),
	   (3, 5);

INSERT INTO [dbo].[shipment] ([description], [name], [shipping_price])
VALUES (N'Giao hàng tận nơi', N'Giao hàng tận nơi', 10.000),
	   (N'Lấy tại của hàng', N'Lấy tại của hàng', 0);

INSERT INTO [dbo].[payment] ([description], [name], [payment_price])
VALUES (N'Thanh toán khi nhận hàng', N'Thanh toán khi nhận hàng', 5.000),
	   (N'Thanh toán qua thẻ tín dụng', N'hanh toán qua thẻ tín dụng', 0);

INSERT INTO [dbo].[category] ([name])
VALUES (N'Khoa học'),
	   (N'Tiểu thuyết'),
	   (N'Kinh dị'),
	   (N'Lịch sử'),
	   (N'Huyền bí'),
	   (N'Viễn tưởng'),
	   (N'Công nghệ');

INSERT INTO [dbo].[book] ([isbn], [author], [average_ratings], [description], [name], [original_price], [price], [quantity])
VALUES ('1234567890123', 'John Doe', 4.5, 'A fascinating book about SQL', 'SQL Mastery', 29.99, 15.99, 100),
	   ('2341617181671', 'Kazuko Watanable', 4.8, N'Cuốn sách “Mình là nắng việc của mình là chói chang” mang đến cho bạn đọc những bài học quý báu về cuộc sống.', N'Mình là nắng việc của mình là chói chang', 23.99, 19.99, 120),
	   ('5432109876543', 'Michael Johnson', 4.3, 'A comprehensive guide to Python programming', 'Python Programming for Beginners', 39.99, 29.99, 120),
	   ('9876543210987', 'Jane Smith', 4.8, 'An engaging novel set in a futuristic world', 'Future Tales', 19.99, 15.50, 75);

INSERT INTO [dbo].[image] ([data], [link], [icon] ,[name] ,[book_id])
VALUES (N'Dữ liệu ảnh 1', 'duongdan1.jpg', 0, N'Ảnh sách 1', 1),
	   (N'Dữ liệu ảnh 2', 'duongdan2.jpg', 0, N'Ảnh sách 2', 2),
	   (N'Dữ liệu ảnh 3', 'duongdan3.jpg', 0, N'Ảnh sách 3', 3),
	   (N'Dữ liệu ảnh 4', 'duongdan4.jpg', 0, N'Ảnh sách 4', 4);

INSERT INTO [dbo].[book_category] ([category_id] ,[book_id])
VALUES (7, 1),
	   (2, 2),
	   (7, 3),
	   (6, 4);

INSERT INTO [dbo].[order] ([create_date] ,[delivery_address] ,[payment_price] ,[purchase_address] ,[shipping_price] ,[total_price], [total_price_book], [payment_id] ,[shipment_id] ,[user_id])
VALUES ('2023-09-20', N'60 Nguyễn Văn Trỗi', 0, N'20 Hoàng Kim', 5.000, 96.95, 91.95, 1, 1, 1),
   	   ('2023-09-21', N'1239 Trần Phú', 2.500, N'20 Lê Văn Việt', 3.000, 35.49, 29.99, 2, 2, 2),
	   ('2023-09-22', N'1239 Trần Hưng Đạo', 0, N'122 Lê Văn Việt', 4.500, 66.50, 62.00, 1, 1, 3);

INSERT INTO [dbo].[order_item] ([price] ,[quantity] ,[order_id] ,[book_id])
VALUES (15.990, 2, 1, 1),
	   (19.99, 3, 1, 2),
	   (29.99, 1, 2, 3),
	   (15.50, 4, 3, 4);