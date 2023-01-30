import numpy as np 
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Conv1D,Input, Dropout,LSTM, Conv2D, Flatten
from sklearn.model_selection import train_test_split
x_datasets = np.array([range(100), range(301,401)]).transpose()
# print (x1_datasets.shape) # (100, 2)


y1 = np.array(range(2001,2101)) # 삼성
y2 = np.array(range(201,301))  # 아모레
x1_train, x1_test,\
    y1_train, y1_test,y2_train,y2_test\
        =train_test_split(x_datasets,
        y1,y2,train_size=0.7,
        random_state=1234)
print (x1_train.shape)

# (70, 2) (70, 3) (70, 2) (70,) (70,)
#2-1 모델
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1)
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
dense4 = Dense(14, activation='relu', name='ds14')(dense3)



from tensorflow.keras.layers import concatenate # 모델들을 사슬 처럼 엮는다
merge1 = concatenate(dense4, name='mg1')
merge2 = Dense(12,activation='relu', name='mg2')(merge1)
merge3 = Dense(13, name='mg3')
last_output = Dense(1, name='last')(merge2)

model = Model(inputs=input1, outputs=last_output)
model.summary()


#2-5 모델

dense51 = Dense(21, activation='relu', name='ds51')(last_output)
dense52 = Dense(22, activation='relu', name='ds52')(dense51)
output5 = Dense(1, activation='relu', name='ds53')(dense52)

#2-6 모델 분기
dense61 = Dense(21, activation='relu', name='ds61')(last_output)
dense62 = Dense(22, activation='relu', name='ds62')(dense61)
output6 = Dense(1, activation='relu', name='ds63')(dense62)

model = Model(inputs=input1,outputs=[output5,output6])

#3 컴파일, 훈련
model.compile(loss = 'mse',optimizer='adam')
model.fit([x1_train],[y1_train,y2_train], epochs=500,batch_size=32)

#4 평가, 예측
loss = model.evaluate([x1_test], [y1_test,y2_test])
print('Loss:', loss)

y_pred = model.predict([x1_test])
print('Prediction:', y_pred)
