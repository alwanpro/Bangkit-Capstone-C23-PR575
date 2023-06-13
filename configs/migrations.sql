INSERT INTO foods (name, food_class, calorie, carb, protein, fat, image_url, default_amount)
VALUES
    ('Ayam Geprek', 'ayam_geprek', 263, 17.99, 7.6, 17.61, 'https://storage.googleapis.com/food-consumptions/default/default_ayam_geprek.png', 100),
    ('Ayam Goreng', 'ayam_goreng', 260, 10.76, 21.93, 14.55, 'https://storage.googleapis.com/food-consumptions/default/default_ayam_goreng.jpeg', 100),
    ('Bakso', 'bakso', 202, 7.58, 12.41, 13.16, 'https://storage.googleapis.com/food-consumptions/default/default_bakso.jpg', 100),
    ('Batagor', 'batagor', 290, 29.14, 10.28, 14.92, 'https://storage.googleapis.com/food-consumptions/default/default_batagor.jpe', 100),
    ('Cendol', 'cendol', 155, 23.49, 1.87, 6.37, 'https://storage.googleapis.com/food-consumptions/default/default_cendol.jpg', 100),
    ('Gorengan', 'gorengan', 228, 11.23, 3.31, 19.31, 'https://storage.googleapis.com/food-consumptions/default/default_gorengan.jpg', 100),
    ('Ikan Goreng', 'ikan_goreng', 128, 0, 26.15, 2.65, 'https://storage.googleapis.com/food-consumptions/default/default_ikan_goreng.jpg', 100),
    ('Ketoprak', 'ketoprak', 201, 25.24, 7.8, 7.67, 'https://storage.googleapis.com/food-consumptions/default/default_ketoprak.jpg', 100),     
    ('Klepon', 'klepon', 366, 65.82, 4.75, 9.34, 'https://storage.googleapis.com/food-consumptions/default/default_klepon.jpg', 100),
    ('Martabak Manis', 'martabak_manis', 300, 41.52, 7.78, 12.57, 'https://storage.googleapis.com/food-consumptions/default/default_martabak_manis.jpeg', 100), 
    ('Martabak Telur', 'martabak_telur', 185, 18.52, 9.9, 7.68, 'https://storage.googleapis.com/food-consumptions/default/default_martabak_telur.jpg', 100),
    ('Mie Ayam', 'mie_ayam', 175, 19.25, 6.96, 7.81, 'https://storage.googleapis.com/food-consumptions/default/default_mie_ayam.jpeg', 100),
    ('Nasi Goreng', 'nasi_goreng', 168, 21.06, 6.3, 6.23, 'https://storage.googleapis.com/food-consumptions/default/default_nasi_goreng.jpg', 100),
    ('Nasi Kuning', 'nasi_kuning', 95, 20.86, 1.89, 0.17, 'https://storage.googleapis.com/food-consumptions/default/default_nasi_kuning.png', 100), 
    ('Nasi Putih', 'nasi_putih', 129, 27.9, 2.66, 0.28, 'https://storage.googleapis.com/food-consumptions/default/default_nasi_putih.jpg', 100),
    ('Onde-Onde', 'onde_onde', 289, 55.49, 4.3, 5.23, 'https://storage.googleapis.com/food-consumptions/default/default_onde_onde.jpg', 100),
    ('Pempek', 'pempek', 195, 23.58, 12.61, 5.2, 'https://storage.googleapis.com/food-consumptions/default/default_pempek.jpg', 100),  
    ('Pisang Ijo', 'pisang_ijo', 150, 23.5, 1.42, 6.64, 'https://storage.googleapis.com/food-consumptions/default/default_pisang_ijo.jpeg', 100),
    ('Rendang', 'rendang', 195, 4.49, 19.68, 11.07, 'https://storage.googleapis.com/food-consumptions/default/default_rendang.jpg', 100),
    ('Roti Bakar', 'roti_bakar', 293, 54.4, 9, 4, 'https://storage.googleapis.com/food-consumptions/default/default_roti_bakar.jpg', 100),
    ('Sate Ayam' ,'sate_ayam', 225, 4.87, 19.54, 14.82, 'https://storage.googleapis.com/food-consumptions/default/default_sate_ayam.jpg', 100),
    ('Soto Ayam', 'soto_ayam', 130, 8.11, 9.96, 6.19, 'https://storage.googleapis.com/food-consumptions/default/default_soto_ayam.jpg', 100),
    ('Sup Ayam', 'sup_ayam', 31, 3.88, 1.68, 1.02, 'https://storage.googleapis.com/food-consumptions/default/default_sup_ayam.jpg', 100),
    ('Telur Balado', 'telur_balado', 202, 3.49, 10.21, 16.43, 'https://storage.googleapis.com/food-consumptions/default/default_telur_balado.jpe', 100);

-- UPDATE IMAGE URL
UPDATE foods SET image_url=tmp.image_url 
FROM (
    VALUES 
        ('Ayam Geprek', 'ayam_geprek', 263, 17.99, 7.6, 17.61, 'https://storage.googleapis.com/food-consumptions/default/default_ayam_geprek.png', 100),
        ('Ayam Goreng', 'ayam_goreng', 260, 10.76, 21.93, 14.55, 'https://storage.googleapis.com/food-consumptions/default/default_ayam_goreng.jpeg', 100),
        ('Bakso', 'bakso', 202, 7.58, 12.41, 13.16, 'https://storage.googleapis.com/food-consumptions/default/default_bakso.jpg', 100),
        ('Batagor', 'batagor', 290, 29.14, 10.28, 14.92, 'https://storage.googleapis.com/food-consumptions/default/default_batagor.jpe', 100),
        ('Cendol', 'cendol', 155, 23.49, 1.87, 6.37, 'https://storage.googleapis.com/food-consumptions/default/default_cendol.jpg', 100),
        ('Gorengan', 'gorengan', 228, 11.23, 3.31, 19.31, 'https://storage.googleapis.com/food-consumptions/default/default_gorengan.jpg', 100),
        ('Ikan Goreng', 'ikan_goreng', 128, 0, 26.15, 2.65, 'https://storage.googleapis.com/food-consumptions/default/default_ikan_goreng.jpg', 100),
        ('Ketoprak', 'ketoprak', 201, 25.24, 7.8, 7.67, 'https://storage.googleapis.com/food-consumptions/default/default_ketoprak.jpg', 100),     
        ('Klepon', 'klepon', 366, 65.82, 4.75, 9.34, 'https://storage.googleapis.com/food-consumptions/default/default_klepon.jpg', 100),
        ('Martabak Manis', 'martabak_manis', 300, 41.52, 7.78, 12.57, 'https://storage.googleapis.com/food-consumptions/default/default_martabak_manis.jpeg', 100), 
        ('Martabak Telur', 'martabak_telur', 185, 18.52, 9.9, 7.68, 'https://storage.googleapis.com/food-consumptions/default/default_martabak_telur.jpg', 100),
        ('Mie Ayam', 'mie_ayam', 175, 19.25, 6.96, 7.81, 'https://storage.googleapis.com/food-consumptions/default/default_mie_ayam.jpeg', 100),
        ('Nasi Goreng', 'nasi_goreng', 168, 21.06, 6.3, 6.23, 'https://storage.googleapis.com/food-consumptions/default/default_nasi_goreng.jpg', 100),
        ('Nasi Kuning', 'nasi_kuning', 95, 20.86, 1.89, 0.17, 'https://storage.googleapis.com/food-consumptions/default/default_nasi_kuning.png', 100), 
        ('Nasi Putih', 'nasi_putih', 129, 27.9, 2.66, 0.28, 'https://storage.googleapis.com/food-consumptions/default/default_nasi_putih.jpg', 100),
        ('Onde-Onde', 'onde_onde', 289, 55.49, 4.3, 5.23, 'https://storage.googleapis.com/food-consumptions/default/default_onde_onde.jpg', 100),
        ('Pempek', 'pempek', 195, 23.58, 12.61, 5.2, 'https://storage.googleapis.com/food-consumptions/default/default_pempek.jpg', 100),  
        ('Pisang Ijo', 'pisang_ijo', 150, 23.5, 1.42, 6.64, 'https://storage.googleapis.com/food-consumptions/default/default_pisang_ijo.jpeg', 100),
        ('Rendang', 'rendang', 195, 4.49, 19.68, 11.07, 'https://storage.googleapis.com/food-consumptions/default/default_rendang.jpg', 100),
        ('Roti Bakar', 'roti_bakar', 293, 54.4, 9, 4, 'https://storage.googleapis.com/food-consumptions/default/default_roti_bakar.jpg', 100),
        ('Sate Ayam' ,'sate_ayam', 225, 4.87, 19.54, 14.82, 'https://storage.googleapis.com/food-consumptions/default/default_sate_ayam.jpg', 100),
        ('Soto Ayam', 'soto_ayam', 130, 8.11, 9.96, 6.19, 'https://storage.googleapis.com/food-consumptions/default/default_soto_ayam.jpg', 100),
        ('Sup Ayam', 'sup_ayam', 31, 3.88, 1.68, 1.02, 'https://storage.googleapis.com/food-consumptions/default/default_sup_ayam.jpg', 100),
        ('Telur Balado', 'telur_balado', 202, 3.49, 10.21, 16.43, 'https://storage.googleapis.com/food-consumptions/default/default_telur_balado.jpe', 100)
    ) AS tmp (name, food_class, calorie, carb, protein, fat, image_url, default_amount) 
WHERE foods.food_class = tmp.food_class;