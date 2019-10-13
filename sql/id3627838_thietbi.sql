-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 02, 2019 at 03:54 PM
-- Server version: 10.3.13-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id3627838_thietbi`
--

-- --------------------------------------------------------

--
-- Table structure for table `Loaisanpham`
--

CREATE TABLE `Loaisanpham` (
  `ID` int(11) NOT NULL,
  `tenloaisanpham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hinhanhloaisanpham` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Loaisanpham`
--

INSERT INTO `Loaisanpham` (`ID`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Điện thoại', 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20loai%20san%20pham/dienthoai.png'),
(2, 'Máy tính', 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20loai%20san%20pham/laptop.png');

-- --------------------------------------------------------

--
-- Table structure for table `Sanpham`
--

CREATE TABLE `Sanpham` (
  `ID` int(11) NOT NULL,
  `tensanpham` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `gia` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `mota` varchar(10000) COLLATE utf8_unicode_ci NOT NULL,
  `IDsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Sanpham`
--

INSERT INTO `Sanpham` (`ID`, `tensanpham`, `gia`, `hinhanhsanpham`, `mota`, `IDsanpham`) VALUES
(1, 'Điện thoại iPhone 7 32GB Chính hãng', 6000000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/iphone-7-black_2_8.jpg', 'Tại sao nên mua iPhone 7 chính hãng VN/A\r\n iPhone 7 32GB chính hãng VN/A tại PDU là hàng chính hãng mà Apple sản xuất dành riêng cho thị trường Việt Nam. Máy được bán mới 100%, nguyên zin, đầy đủ phụ kiện và chưa được active.\r\nVậy tại sao nên mua iPhone 7 chính hãng VN/A thay vì mua hàng xách tay giá rẻ hơn. Đầu tiên, khi mua iPhone 7 chính hãng VN/A khách hàng sẽ được hưởng một chính sách bảo hành tốt. Nghĩa là khi mua iPhone 7 mã VN/A bạn có thể bảo hành ở tất cả các nơi có bán hàng chính hãng của Apple chứ không nhất định phải là nơi bạn mua máy.\r\nThứ hai là máy mới chính hãng nên máy vẫn được hưởng bảo hành 1 đổi 1 trong 1 năm của Apple. Ngoài ra, iPhone 7 chính hãng VN/A sẽ được áp dụng bảo hành tại các trung tâm bảo hành được Apple ủy quyền tại Việt Nam.', 1),
(2, 'Điện thoại iPhone 6 32gb', 2499000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/Apple-iPhone-6-32GB.jpg', 'Apple iPhone 6 32GB Chính hãng (Mã VN/A)\r\n Mặc dù đã ra đời cách đây khá lâu, điện thoại Apple iPhone 6 32GB Chính hãng vẫn được nhiều người sử dụng điện thoại Apple ưu chuộng bởi nhiều ưu điểm trong thiết kế cao cấp, sự phối hợp hoàn hảo giữa phần cứng và phần mềm, đem đến cho bạn một siêu phẩm trong tầm tay.', 1),
(3, 'Điện thoại Samsung Galaxy S9', 9499000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/samsung-galaxy-s9-black-600x600.jpg', 'Samsung Galaxy S9 Chính hãng\r\nCứ mỗi năm, hãng điện thoại Samsung lại giới thiệu thêm flagship tại sự kiện MWC. Và năm nay, sự xuất hiện của Samsung Galaxy S9 Plus là tâm điểm, thu hút mọi ánh nhìn của giới công nghệ. Vẫn giữ hình dáng, thiết kế tương tự như Galaxy S8, S9 sẽ được nâng cấp về mặt cấu hình, camera và những tính năng mới.', 1),
(4, 'Laptop Dell G3 3579 Core i7', 23200000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/dell-3579-g3_3.png', ' Mới đây, Dell đã chính thức tung ra dòng sản phẩm laptop gaming G-Series mới tới người dùng, với các mẫu sản phẩm là G3 – G5 và G7. Đây là dòng sản phẩm mà Dell muốn nhắm đến người dùng với phân khúc giá phổ thông. Và G3 3579 là dòng laptop gaming rẻ nhất của Dell với mức giá chỉ từ 22.590.000đ với cấu hình là i5 8300H, 8Gb RAM và sử dụng card đồ họa rời GTX1050 4Gb. Nhiều bạn đang thắc mắc rằng, liệu nó có thực sự đáng đồng tiền bát gạo để bạn bỏ ra mua hay không? Hay so với tầm giá này liệu nó thực sự tốt? Hãy cùng mình đi tìm hiểu kĩ hơn về chiếc máy này nhé.\r\nĐầu tiên, chúng ta sẽ có được một thiết kế sẽ được làm hoàn toàn từ nhựa, mà hầu hết các mẫu G5 và G7 cũng sẽ y như vậy. Tông màu chủ đạo trên G3 sẽ là màu đen bao trùm được tô điểm bởi các chi tiết màu xanh dương quen thuộc Dell.\r\nPhần chính giữa vỏ máy sẽ là logo Dell màu xanh được làm bằng kim loại. Mình khá thích thiết kế Logo này của nó. Mở máy ra thì mình có đôi phần cảm thấy hơi nuối tiếc vì Dell đã không thực sự tạo được điểm nhấn nổi bật cho G3. Cũng có thể vì màu xanh dương này không nổi bật và tạo chất gaming tốt như màu đỏ quen thuộc được nhiều nhà sản xuất áp dụng.\r\nTuy nhiên, kết cấu thì lại thực sự tốt và chắc chắn. Dường như không có 1 điểm yếu nào cho phần khung máy.\r\nPhần phía trên của khu vực màn hình có thể cong nhẹ do các tác động, nhưng nó vẫn thực sự tốt. Điều đó có thể dễ hiểu khi G3 chỉ được làm bằng nhựa chứ không phải bằng nhôm.\r\nMặt đáy máy có thiết kế rất tối giản với 2 chân cao su trải dải ở phía trên và dưới máy để chống trơn trượt, ở giữa sẽ là các khe hút gió nhỏ. Về tổng thể, G3 là chiếc máy mỏng nhẹ hơn so với G5 và G7. G3 sẽ có kích thước 380 x 258 x 22.7mm và cân nặng khoảng 2.53kg. Phần đáy máy được chốt giữ bởi 10 con ốc, điều đó sẽ khiến việc tháo ra nâng cấp phần cứng bên trong sẽ đôi chút khó khăn. Về nội thất bên trong, chúng ta sẽ có 2 khe cắm RAM, cho khả năng nâng cấp tối đa tới 32Gb RAM. 1 Khu vực dành cho HDD, cũng như có 1 khe cắm SSD M2 tại phía đối xứng. Hệ thống tản nhiệt hiện lên rõ ràng với 2 quạt làm mát cùng với 2 ống đồng. Về tổng quan, thiết kế bên trong của chiếc laptop Dell G3 15 này khá tinh tế và có thể vệ sinh dễ dàng.\r\nVới việc được trang bị cục Pin 4 cell 56Whr, máy sẽ cho thời gian sử dụng khoảng 4 tiếng đồng hồ. Vì vậy, nếu phải mang đi học cả ngày, bạn hãy nên nhớ mang theo cục sạc để có thể cắm sạc khi cần thiết. ', 2),
(5, 'Laptop Dell Precision M4800 VGA K1100M', 12100000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/dell-m4800.png', 'PDU Laptop để cấu hình rõ ràng, không mập mờ như nhiều công ty khác trên thị trường, nhiều tùy chọn Option miễn phí cho khách hàng. Tránh tình trạng giá một kiểu, lúc đến xem lại mất thêm rất nhiều khoản phí khác! Các bạn đặc biệt lưu ý vấn đề này. Xin cảm ơn!\r\n\r\nCPU Core i7 4800MQ – 2.7GHz - Turbo 8x3.7Ghz \r\n\r\nRAM3 8GB PC3L, 4 khe cắm RAM, hỗ trợ nâng cấp tối đa lên 32Gb\r\n\r\nỔ cứng HDD 500GB SATA - Hỗ trợ nâng cấp SSD với giá ưu đãi.\r\n\r\nVGA Quadro K1100 - 2Gb GDR5 - VGA chuyên nghiệp cho thiết kế (Render)\r\n\r\nMàn hình 15.6 inch LED FULL HD (1920 x 1080) - Có tùy chọn màn Full HD tấm nền IPS (Model 2015)', 2),
(6, 'Điện thoại LG G6 64GB Bản Hàn Quốc (Likenew)', 3990000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/lgg6.jpg', 'Không quá rầm rộ những LG G6 64GB cũ Hàn Quốc vẫn tỏa sáng theo cách của riêng mình và có sức cạnh tranh khá lớn so với các smartphone  trong cùng tầm giá. Đặc biệt LG G6 cũ đang có mức giá ngày càng rẻ hơn chỉ dừng lại ở phân khúc 3 triệu đồng. \r\nThiết kế đơn giản, nhẹ nhàng \r\nLG G6 64GB cũ vẫn sở hữu ngoại hình khá đơn giản và nhẹ nhàng, tuy nhiên vẫn giữ nét sang trọng vốn có của những smartphone cao cấp. Được kết hợp từ sự chắc chắn của kim loại và sự sang trọng của 2 mặt kính (Gorilla 3 và Gorilla Glass 5) mang đến sự bóng bẩy và bắt mắt cho LG G6. \r\nTuy nhiên, với mặt lưng kính thì rất dễ bám vân tay và đây là một điểm khiến người dùng khó chịu, nhưng đây là điều không tránh khỏi trên hầu hết các smartphone có mặt lưng kính hiện nay. \r\n\r\nMàn hình chi tiết hiển thị tốt\r\nMàn hình là điểm được yêu thích nhất trên LG G6 64GB cũ Hàn Quốc, bởi những lý do sau:\r\n\r\nLG G6 chính hãng được trang bị màn hình 5.2 có độ hiển thị các tông màu tương đối chính xác, màu sắc chi tiết, hiển thị tốt khi ở ngoài trời....\r\nTủy lệ màn hình 18:9 cho diện tích hiển thị nhiều hơn\r\nCác phím chức năng được cài sẵn trong màn hình LG G6\r\nGiao diện, icon được hài hòa và tiện lợi, không quá sặc sỡ, chói mắt.\r\nHiệu năng vẫn đáp ứng tốt đến thời điểm hiện tại\r\nMột số người dùng đã thật sự thất vọng khi LG đã không trang bị con chip mạnh nhất cho LG G6 tại thời điểm ra mắt. Tuy LG G6 64GB cũ chỉ được trang bị chip xử lý Snapdragon 821 thay vì Snapdragon 835 như những flagship cao cấp khác. Nhưng vẫn mang đến trải nghiệm thực tế tuyệt vời khi máy đáp ứng tốt các nhu cầu cơ bản của người dùng một cách mượt mà và trơn tru.\r\nBên cạnh đó LG G6 64GB cũ Hàn Quốc còn sở hữu dung lượng bộ nhớ lưu trữ RAM 4GB và bộ nhớ trong 64GB và GPU Adreno 530. Điều này cho thấy LG G6 là một trong những smartphone được đánh giá là tốt nhất trong năm 2016 và cho đến thời điểm hiện tại. \r\n\r\nCamera kép chụp ảnh góc rộng tuyệt vời\r\nCamera là điểm đáng chú ý trên LG G6 cũ Hàn Quốc, máy được trang bị hệ thống camera kép có độ phân giải là 13MP cho góc chụp khá rộng lên đến 125 độ, camera còn lại cho góc rộng 70 độ. Khả năng chụp ảnh xóa phông khá ấn tượng trên LG G6, có thể vượt hơn hẳn chiếc iPhone 7 Plus của Apple. \r\nBên cạnh những ưu điểm trên thì LG G6 64GB cũ còn được trang bị thêm các tính năng như: hỗ trợ lấy nét tự động theo pha, chống rung quang học, thu sáng tốt. Mặt trước được trang bị một camera 5MP chụp góc rộng 100 độ, chụp ảnh selfie tương đối.\r\n\r\nThời lượng pin kéo dài hơn 1 ngày \r\nVới năng lương pin 3.300 mAh lại phải chịu đựng màn hình độ phân giải QHD+ thì liệu LG G6 cũ có giải quyết tốt vấn đề về thời lượng không? đây là một câu hỏi khó. Tuy nhiên, người dùng phải thật sự bất ngờ khi máy được có thể trụ được hơn 1 ngày rưỡi cho các như cầu cơ bản như lướt web, chơi game...và đặc biệt LG G6 64GB cũ còn được trang bị công nghệ sạc nhanh Quick Chage 3.0.\r\nNếu như bạn thích sự nhẹ nhàng, đơn giản...và ẩn chứa sức mạnh bên trong thì LG G6 64GB cũ là chiếc smartphoen bạn không nên bỏ qua. ', 1),
(7, 'Điện thoại LG G7 ThinQ Bản Mỹ (Likenew)', 6990000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/lgg7.jpg', 'Nếu bạn là Fan của LG G7 ThinQ cũ bản Mỹ ngoài lợi thế về giá thành rẻ, thì từ thiết kế đến cấu hình hoàn toàn giống như một chiếc LG G7 ThinQ mới. Cùng với đó thiết bị còn được trang bị hàng loạt tính năng cao cấp hiện đại. \r\nThiết kế sang trọng với mặt kính bóng bẩy\r\nLG G7 ThinQ bản Mỹ cũ vẫn giữ được nét sang trọng với một tấm kính ở phía mặt lưng. Bên cạnh đó, các cạnh của phần lưng được bo cong tròn theo khung máy một cách khéo léo. Riêng phần cạnh trái của máy tích hợp 2 nút tăng giảm âm lượng và 1 nút dành cho truy cập nhanh trợ lý ảo Google.\r\nMàn hình cực kì sắc nét\r\nLG trang bị cho G7 xách tay Mỹ màn hình IPS LCD 6.1 inch với các phần viền khá mỏng theo tỉ lệ 19,5:9 có độ phân giải lên đến 3120 x 1440 pixel. LG quyết định tối ưu khá tốt khu vực đó bằng cách cho phép người dùng đặt nó làm màn hình thứ hai nơi đặt thông báo và thông tin trạng thái. Với những trang bị hiện đại trên LG G7 ThinQ bản Mỹ cũ đã giúp hình ảnh hiển thị cực kì sắc nét, đặc biệt màu sắc sống động, chân thực. \r\nHiệu năng mạnh mẽ \r\nLG G7 ThinQ bản Mỹ cũ vẫn được trang bị chip Snapdragon 845 mới nhất cùng với 4GB bộ nhớ RAM và 64GB bộ nhớ lưu trữ. Máy cũng được chạy trên nền tảng Android 8.1 Oreo cùng với những tùy chỉnh giao diện riêng của LG. Vì vậy việc tối ưu hóa của thiết bị cũng được cải thiện so với các dòng thiết bị tiền nhiệm của LG. Do đó, người dùng có thể thoải mái trải nghiệm những tựa game đồ họa từ nhẹ đến nặng mà không phải lo bị đứng máy hay giật lag.\r\nDung lượng pin chỉ là 3.000mAh nhưng cho đến nay, LG G7 vẫn có thể kéo dài cả ngày sử dụng vừa phải. Đây vẫn là một thông số pin phổ biến trên các dòng smartphone cao cấp hiện nay. Tin vui là chiếc điện thoại LG này cũng hỗ trợ sạc nhanh Quick Charge 3.0 và cũng có sạc không dây.\r\n\r\nCamera ấn tượng hỗ trợ nhiều tính năng độc đáo\r\nBên cạnh đó, LG G7 ThinQ bản Mỹ cũ sở hữu camera kép 16 MP mặt sau, cả 2 đều có khẩu độ F/1.6. Ngoài ra, LG G7 ThinQ cũ cũng vẫn sử dụng viên pin 3.000 mAh, có hỗ trợ sạc nhanh Quick Charge 4.0. Người dùng có thể lựa chọn theo ý thích khi G7 ThinQ có nhiều màu sắc khác nhau: Aurora Black, Platinum Grey, Raspberry Rose và cả các tùy chọn màu Moroccan Blue nhám hoặc bóng.\r\nVới những trang bị hiện đại cũng như sở hữu thiết kế sang trọng thì G7 ThinQ cũ xách tay Mỹ cũng chẳng khác gì một chiếc máy mới hoàn toàn, điều đặc biệt là giá lại thấp hơn rất nhiều, vậy còn chần chờ gì mà không mau sở hữu một chiếc LG G7 cũ bản Mỹ.', 1),
(8, 'Điện thoại Samsung Galaxy S8 chính hãng', 5990000, 'https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20san%20pham/samsung-galaxy-s8-4-400x460-400x460.png', 'Galaxy S8 được dự đoán là một siêu phẩm mà Samsung sẽ giới thiệu đến người dùng trong thời gian gần nhất. Theo như hình ảnh của sản phẩm mới được hé lộ, Samsung S8 sẽ sở hữu một thiết kế ấn tượng, hiệu năng cực kỳ mạnh mẽ và tiết kiệm năng lượng, camera sắc nét cùng màn hình Infinity Display, hứa hẹn sẽ trở thành một đối thủ đáng gờm trên thị trường smartphone năm 2017.\r\n\r\n \r\n\r\nSở hữu một thiết kế mới ấn tượng, cùng một vẻ ngoài sang trọng.\r\nNhững hình ảnh rò rỉ đáng tin cậy gần đây cho thấy Samsung Galaxy S8 sẽ sở hữu một vẻ ngoài ấn tượng với thiết kế “không viền màn hình”, ngoài việc cong tràn hai mép, màn hình của S8 còn được cắt giảm phần cạnh trên và cạnh dưới đến tối đa. Kết hợp với bộ khung kim loại chắc chắn và kính cong 3D sẽ tạo ra một vẻ ngoài cứng cáp và sang trọng.\r\n\r\n \r\n\r\nMột hiệu năng mạnh mẽ\r\n \r\n\r\nTheo như thông tin trên website GSMARENA thì Galaxy S8 có thể sẽ được trang bị bộ vi xử lý 8 nhân Qualcomm Snapdragon 835 (4 x 2.45 GHz Kryo & 4 x 1.9 GHz Kryo). Tuy nhiên, một số nguồn tin khác lại đưa ra thông tin rằng S8 sẽ được sở hữu bộ vi xử lý Samsung Exynos 8895 được sản xuất với công nghệ 10 nm đem lại hiệu năng mạnh hơn 27% và tiêu thụ điện năng thấp hơn 40% so với bộ vi xử lý của Galaxy S7.\r\nDung lượng RAM được dự đoán là 4 GB và bộ nhớ trong 64 GB. Với chip đồ họa Adreno 540 thì dù điện thoại Galaxy S8 được trang bị bộ vi xử lý nào trong hai bộ vi xử lý trên thì cũng chắc chắn mang lại cho máy một hiệu năng mạnh mẽ, ổn định, đủ đế đáp ứng những yêu cầu khắt khe của người dùng.\r\n\r\n \r\n\r\nMàn hình Infinity Display – Màn hình vô cực.\r\nNhững hình ảnh rò rỉ đều cho thấy Samsung S8 sẽ được trang bị màn hình kích thước 5.8 inch với độ phân giải 2560 x 1440 pixels cùng công nghệ màn hình Super AMOLED sẽ đem lại những hình ảnh sắc nét nhất, màu sắc chân thật nhất. Galaxy S8 có thể sẽ sử dụng thiết kế màn hình Infinity Display đem đến cho người dùng những trải nghiệm tuyệt vời khi xem phim, lướt web, facebook. Ngoài ra, mặt kính Corning Gorilla Glass 5 đem lại một sự bảo vệ tốt hơn.\r\nSamsung Galaxy S8 sở hữu khả năng kháng bụi, nước một cách tuyệt vời\r\nKhả năng kháng bụi và nước là một điều không thể thiếu đối với các dòng sản phẩm cao cấp, cùng với đó sự thành công của Samsung Galaxy S7 sẽ là một tiền đề tốt đế, Samsung trang bị cho Galaxy S8 những tính năng như vậy. Như vậy có thể thấy rằng, S8 cũng có thể sở hữu khả năng chống bụi và nước đạt chuẩn IP68.', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Loaisanpham`
--
ALTER TABLE `Loaisanpham`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Sanpham`
--
ALTER TABLE `Sanpham`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Loaisanpham`
--
ALTER TABLE `Loaisanpham`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Sanpham`
--
ALTER TABLE `Sanpham`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
