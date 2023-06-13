import numpy as np
import os
import glob
import PIL.Image

from tflite_model_maker.config import ExportFormat
from tflite_model_maker import model_spec
from tflite_model_maker import object_detector

import tensorflow as tf
assert tf.__version__.startswith('2')

tf.get_logger().setLevel('ERROR')
from absl import logging
logging.set_verbosity(logging.ERROR)

spec = model_spec.get('efficientdet_lite2')

train_data = object_detector.DataLoader.from_pascal_voc("/content/Nutriku-7/train", "/content/Nutriku-7/train", ["ayam_geprek", "ayam_goreng", "bakso", "batagor", "cendol", "gorengan", "ikan_goreng", "ketoprak", "klepon", "martabak_manis", "martabak_telur", "mie_ayam", "nasi_goreng", "nasi_kuning", "nasi_putih", "onde-onde", "pempek", "pisang_ijo", "rendang", "roti_bakar", "sate_ayam", "soto_ayam", "sup_ayam", "telur_balado"])

validation_data = object_detector.DataLoader.from_pascal_voc('/content/Nutriku-7/valid', '/content/Nutriku-7/valid', ["ayam_geprek", "ayam_goreng", "bakso", "batagor", "cendol", "gorengan", "ikan_goreng", "ketoprak", "klepon", "martabak_manis", "martabak_telur", "mie_ayam", "nasi_goreng", "nasi_kuning", "nasi_putih", "onde-onde", "pempek", "pisang_ijo", "rendang", "roti_bakar", "sate_ayam", "soto_ayam", "sup_ayam", "telur_balado"])

test_data = object_detector.DataLoader.from_pascal_voc('/content/Nutriku-7/test', '/content/Nutriku-7/test', ["ayam_geprek", "ayam_goreng", "bakso", "batagor", "cendol", "gorengan", "ikan_goreng", "ketoprak", "klepon", "martabak_manis", "martabak_telur", "mie_ayam", "nasi_goreng", "nasi_kuning", "nasi_putih", "onde-onde", "pempek", "pisang_ijo", "rendang", "roti_bakar", "sate_ayam", "soto_ayam", "sup_ayam", "telur_balado"])


model = object_detector.create(train_data, model_spec=spec, batch_size=24, train_whole_model=True, validation_data=validation_data)

model.evaluate(test_data)

model.export(export_dir='/content/model')