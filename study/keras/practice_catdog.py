# Reproducability
seed_value = 10

import os
os.environ['PYTHONHASHSEED'] = str(seed_value)

import random
random.seed(seed_value)

import numpy as np
np.random.seed(seed_value)

import tensorflow as tf
tf.random.set_seed(seed_value)

# Necessary imports
import pandas as pd
import matplotlib.pyplot as plt 
plt.style.use("ggplot")
import seaborn as sns

import warnings
warnings.filterwarnings("ignore")

from glob import glob

file_names = glob('d:./_data/catdog/train/')
categories = [1 if 'dog' in pic else 0 for pic in os.listdir("d:./_data/catdog/train/")]

df = pd.DataFrame({'filename': file_names, 'category':categories})
df["category"] = df["category"].replace({0: 'cat', 1: 'dog'})

print("shape:", df.shape)
df.head()

from sklearn.model_selection import train_test_split

train_df, validate_df = train_test_split(df, test_size=0.2,
                                         random_state=10)
train_df = train_df.reset_index(drop=True)
validate_df = validate_df.reset_index(drop=True)

# print shape
train_df.shape, validate_df.shape

# plot label counts
fig, ax = plt.subplots(nrows=1, ncols=2, figsize=(10,5))

sns.countplot(train_df.category, ax=ax[0])
ax[0].set_title('Training dataset', fontsize=14)

sns.countplot(validate_df.category, ax=ax[1])
ax[1].set_title('Valiadtion dataset', fontsize=14)

file_names = os.listdir("./test1")

test_df = pd.DataFrame({'filename': file_names})

print("shape:", test_df.shape)
test_df.head()

from tensorflow.keras.preprocessing.image import ImageDataGenerator

IMAGE_SIZE = (190,190)
BATCH_SIZE = 32

datagen = ImageDataGenerator(rescale = 1/255)

train_generator = datagen.flow_from_dataframe(
    dataframe=train_df,
#     directory='./Images/train', 
    x_col='filename',
    y_col='category',
    class_mode='binary',
    target_size=IMAGE_SIZE,
    batch_size=BATCH_SIZE
)

valid_generator = datagen.flow_from_dataframe(
    dataframe=validate_df,
#     directory='./Images/train',
    x_col='filename',
    y_col='category',
    class_mode='binary',
    target_size=IMAGE_SIZE,
    batch_size=BATCH_SIZE
)

test_generator = datagen.flow_from_dataframe(
    dataframe=test_df,
    directory="./test1",
    x_col='filename',
    y_col=None,
    class_mode=None,
    target_size=IMAGE_SIZE,
    batch_size=BATCH_SIZE
)

# get a batch of 32 training images 
images = train_generator.next()[:9]

# plot 9 original training images
plt.figure(figsize=(10, 10))
for i in range(9):
    plt.subplot(3, 3, i+1)
    plt.imshow(images[0][i])
    plt.axis('off')
plt.tight_layout()
plt.show()

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Input, Rescaling, Conv2D, MaxPool2D, Flatten, Dense

# Design the model architecture
model_1 = Sequential([
    Input(shape=(190,190,3)),
    Conv2D(filters=32, kernel_size=3, padding='same', activation='relu'),
    MaxPool2D(pool_size=2),
    Conv2D(filters=64, kernel_size=3, padding='same', activation='relu'),
    MaxPool2D(pool_size=2),
    Conv2D(filters=128, kernel_size=3, padding='same', activation='relu'),
    MaxPool2D(pool_size=2),
    Conv2D(filters=265, kernel_size=3, padding='same', activation='relu'),
    Flatten(),
    Dense(1, activation='sigmoid')  # Cat or dog  
])

model_1.summary()

# Compile model
model_1.compile(loss="binary_crossentropy",
                optimizer="adam", metrics=["accuracy"])

from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping, ReduceLROnPlateau

# callbacks is a reguralization technique to brevent over fitting
callbacks = [
    # to stop training when you measure that the validation loss is no longer improving
    EarlyStopping(patience=4, monitor='val_loss'),
    # reduce learning_rate if the model is not imporving
    ReduceLROnPlateau(monitor='val_acc', patience=2, verbose=1, factor=0.5, min_lr=0.00001),
    # save best model
    ModelCheckpoint(filepath='./Models/model_1.keras', save_best_only=True, monitor='val_loss')
]

# train the model
history_1 = model_1.fit(train_generator, validation_data=valid_generator, epochs=10, callbacks=[callbacks])

