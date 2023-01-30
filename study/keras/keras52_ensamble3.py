import numpy as np 
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Conv1D,Input, Dropout,LSTM, Conv2D, Flatten
from sklearn.model_selection import train_test_split
x1_datasets = np.array([range(100), range(301,401)]).transpose()
# print (x1_datasets.shape) # (100, 2)

x2_datasets = np.array([range(101,201), range(411,511),range(150,250)]).T
# print (x2_datasets.shape) # (100, 3)
x3_datasets = np.array([range(100,200),range(1301,1401)]).T
y = np.array(range(2001,2101)) # 삼성
y2 = np.array(range(201,301))  # 아모레
x1_train, x1_test, x2_train, x2_test,x3_train,x3_test,y_train, y_test,y2_train,y2_test =train_test_split(x1_datasets,
                                                                       x2_datasets,x3_datasets,
                                                                       y,y2,train_size=0.7,
                                                                       random_state=1234)

print(x1_train.shape,x2_train.shape,x3_train.shape,y2_train.shape)

#2-1 모델
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1)
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
dense4 = Dense(14, activation='relu', name='ds14')(dense3)

#2-2 모델
input2 = Input(shape=(3,))
dense21 = Dense(21, activation='relu', name='ds21')(input2)
dense22 = Dense(22, activation='relu', name='ds22')(dense21)
dense23 = Dense(23, activation='relu', name='ds23')(dense22)

#2-3 모델
input3 = Input(shape=(2,))
dense31 = Dense(21, activation='relu', name='ds31')(input3)
dense32 = Dense(22, activation='relu', name='ds32')(dense31)
dense33 = Dense(23, activation='relu', name='ds33')(dense32)

from tensorflow.keras.layers import concatenate # 모델들을 사슬 처럼 엮는다
merge1 = concatenate([dense4, dense23,dense33], name='mg1')
merge2 = Dense(12,activation='relu', name='mg2')(merge1)
merge3 = Dense(13, name='mg3')
last_output = Dense(1, name='last')(merge2)

model = Model(inputs=[input1, input2, input3], outputs=last_output)
model.summary()





#3 컴파일, 훈련
model.compile(loss = 'mse',optimizer='adam')
model.fit([x1_train,x2_train,x3_train],y_train, epochs=500,batch_size=32)

#4 평가, 예측
loss = model.evaluate([x1_test, x2_test, x3_test], y_test)
print('Loss:', loss)

y_pred = model.predict([x1_test, x2_test, x3_test])
print('Prediction:', y_pred)
