import { calculateDailyCalorie } from '../utils/index.js';
import { createProfile } from '../services/user_data.js';
// import { createProfile, updateProfile } from '../services/user_data.js';


export const createUserData = async (req, res) => {
  const { weight, height, gender, birth_date, activity } = req.body;

  // Menghitung kalori harian berdasarkan data pengguna
  const target = await calculateDailyCalorie(req.body);

  const userData = {
    weight: weight,
    height: height,
    gender: gender,
    birth_date: birth_date,
    activity: activity,
    target: target,
  };

  try {
    const createdProfile = await createProfile(userData);

    res.json({
      message: 'create user-data success',
      status: 'success',
      data: createdProfile
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({
      message: 'Failed to create user-data',
      status: 'error'
    });
  }
};



// export const updateUserData = async (req, res) => {
//   const { weight, height } = req.body;

//   try {
//     const userData = req.body;

//     // Menghitung kalori harian berdasarkan data pengguna yang diperbarui
//     const target = calculateDailyCalorie(userData);

//     // Memperbarui target kalori harian di objek userData
//     userData.target = target;

//     const updatedProfile = await updateProfile(userData);

//     res.json({
//       message: 'Berat dan tinggi badan berhasil diperbarui',
//       status: 'success',
//       data: updatedProfile,
//     });
//   } catch (error) {
//     console.error(error);
//     res.status(500).json({
//       message: 'Gagal memperbarui berat dan tinggi badan',
//       status: 'error',
//     });
//   }
// };