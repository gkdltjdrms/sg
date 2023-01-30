import numpy as np 
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Dense, concatenate
from sklearn.model_selection import train_test_split

x1_datasets = np.array([range(100), range(301,401)]).transpose()
x2_datasets = np.array([range(101,201), range(411,511),range(150,250)]).T
x3_datasets = np.array([range(100,200),range(1301,1401)]).T
y = np.array(range(2001,2101))
y2 = np.array(range(201,301))

x1_train, x1_test, x2_train, x2_test,x3_train,x3_test,y_train, y_test,y2_train,y2_test = train_test_split(
    x1_datasets, x2_datasets,x3_datasets, y, y2, train_size=0.7, random_state=1234
)

# Model 1
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu')(input1)
dense2 = Dense(12, activation='relu')(dense1)
dense3 = Dense(13, activation='relu')(dense2)
dense4 = Dense(14, activation='relu')(dense3)

# Model 2
input2 = Input(shape=(3,))
dense21 = Dense(21, activation='linear')(input2)
dense22 = Dense(22, activation='linear')(dense21)
dense23 = Dense(23, activation='linear')(dense22)

# Model 3
input3 = Input(shape=(2,))
dense31 = Dense(21, activation='linear')(input3)
dense32 = Dense(22, activation='linear')(dense31)
dense33 = Dense(23, activation='linear')(dense32)

# Merge Models
merge = concatenate([dense4, dense23,dense33])
dense_merge = Dense(12, activation='relu')(merge)
dense_merge2 = Dense(13)(dense_merge)
output = Dense(1)(dense_merge2)

model = Model(inputs=[input1, input2, input3], outputs=output)
model.compile(loss='mse', optimizer='adam')
model.fit([x1_train, x2_train, x3_train], y_train, epochs=500, batch_size=32)

# Evaluate
loss = model.evaluate([x1_test, x2_test, x3_test], y_test)
print('Loss:', loss)
y_predict = model.predict([x1_test, x2_test, x3_test])
print('Prediction:', y_predict)
