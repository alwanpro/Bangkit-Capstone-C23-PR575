import { storage } from '../configs/storage.js';
import mime from 'mime';
import * as util from 'util';

const BUCKET_NAME = 'food-consumptions';
const bucket = storage.bucket(BUCKET_NAME);

const uploadFile = async (file, userId, foodClass) => {
  const { originalname, buffer } = file;
  const now = new Date();
  const fileExtension = originalname.split('.').pop();
  const filePath = `${userId}/${foodClass}_${now.toISOString()}.${fileExtension}`;

  const blob = bucket.file(filePath);

  return new Promise((resolve, reject) => {
    const blobStream = blob.createWriteStream({
      resumable: false,
    });

    blobStream
      .on('finish', async () => {
        const publicUrl = util.format(
          `https://storage.googleapis.com/${BUCKET_NAME}/${filePath}`
        );

        await bucket.file(filePath).makePublic();

        resolve(publicUrl);
      })
      .on('error', (err) => {
        reject(`Unable to upload image, server error`);
      })
      .end(buffer);
  });
};

export { uploadFile };
