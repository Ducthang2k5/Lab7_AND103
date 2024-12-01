const express = require('express');
const cors = require('cors');
const app = express();
const PORT = 3003;

app.use(cors());
app.use(express.json());

// Danh sách trái cây mẫu
const fruits = [
    { id: '1', name: 'VISION', color: 'Red', price: 3000000 },
    { id: '2', name: 'SH', color: 'Yellow', price: 4550000 },
    { id: '3', name: 'AIR BLADE', color: 'Orange', price: 900000 }
];

// API: Lấy danh sách tất cả trái cây
app.get('/get-all-fruits', (req, res) => {
    res.status(200).json(fruits);
});

// API: Lấy thông tin chi tiết trái cây theo ID
app.get('/get-fruit-by-id/:id', (req, res) => {
    const fruit = fruits.find(f => f.id === req.params.id);
    if (fruit) {
        res.status(200).json(fruit);
    } else {
        res.status(404).json({ message: 'Fruit not found' });
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
