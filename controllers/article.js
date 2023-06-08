const articles = [
  {
    id: '1',
    title: 'Dasar-dasar nutrisi makanan',
    body: 'https://storage.googleapis.com/article-nutriku/Dasar-dasar%20nutrisi%20makanan/Dasar-dasar%20nutrisi%20makanan.txt',
    img: 'https://storage.googleapis.com/article-nutriku/Dasar-dasar%20nutrisi%20makanan/Img-Dasar-dasar%20nutrisi%20makanan.jpg'
  },
  {
    id: '2',
    title: 'Kesadaran nutrisi makanan',
    body: 'https://storage.googleapis.com/article-nutriku/Kesadaran%20nutrisi%20makanan/Pentingnya%20Kesadaran%20Nutrisi.txt',
    imag: 'https://storage.googleapis.com/article-nutriku/Kesadaran%20nutrisi%20makanan/Img-Pentingnya%20Kesadaran%20Nutrisi.jpg'
  },
  {
    id: '3',
    title: 'Mengatur porsi makanan dan gaya hidup seimbang',
    body: 'https://storage.googleapis.com/article-nutriku/Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang/Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang.txt',
    img: 'https://storage.googleapis.com/article-nutriku/Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang/Img-Mengatur%20porsi%20makanan%20dan%20gaya%20hidup%20seimbang.jpg'
  },
  {
    id: '4',
    title: 'Dampak dan cara menghindari konsumsi gula berlebihan',
    body: 'https://storage.googleapis.com/article-nutriku/Dampak%20dan%20Cara%20Menghindari%20Konsumsi%20Gula%20Berlebihan/Dampak%20dan%20cara%20menghindari%20konsumsi%20gula%20berlebihan.txt',
    img: 'https://storage.googleapis.com/article-nutriku/Dampak%20dan%20Cara%20Menghindari%20Konsumsi%20Gula%20Berlebihan/Img-Dampak%20dan%20cara%20menghindari%20konsumsi%20gula%20berlebihan.jpg'
  },
];

export const getArticles = (req, res) => {
  try {
    res.json({
      message: 'Success retrieving articles',
      status: 'success',
      data: articles,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({
      message: 'Failed to retrieve articles',
      status: 'error',
    });
  }
};

export const getArticleById = (req, res) => {
  const articleId = req.params.id;

  const article = articles.find((article) => article.id === articleId);

  if (article) {
    res.json({
      message: 'Success retrieving article',
      status: 'success',
      data: article,
    });
  } else {
    res.status(404).json({
      message: 'Article not found',
      status: 'error',
    });
  }
};