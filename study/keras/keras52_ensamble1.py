# https://ebbnflow.tistory.com/133 ensamble
import numpy as np 
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Conv1D,Input, Dropout,LSTM, Conv2D, Flatten
x1_datasets = np.array([range(100), range(301,401)]).transpose()
# print (x1_datasets.shape) # (100, 2)

x2_datasets = np.array([range(101,201), range(411,511),range(150,250)]).T
# print (x2_datasets.shape) # (100, 3)

y = np.array(range(2001,2101)) # 삼성전자의 하루뒤 주가

from sklearn.model_selection import train_test_split
x1_train, x1_test, x2_train, x2_test,y_train, y_test =train_test_split(x1_datasets,
                                                                       x2_datasets,
                                                                       y,train_size=0.7,
                                                                       random_state=1234)

print(x1_train.shape,x2_train.shape)

#2-1 모델
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1)
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
dense4 = Dense(14, activation='relu', name='ds14')(dense3)

#2-2 모델
input2 = Input(shape=(3,))
dense21 = Dense(21, activation='linear', name='ds21')(input2)
dense22 = Dense(22, activation='linear', name='ds22')(dense21)
dense23 = Dense(23, activation='linear', name='ds23')(dense22)



from tensorflow.keras.layers import concatenate # 모델들을 사슬 처럼 엮는다
merge1 = concatenate([dense4, dense23], name='mg1')
merge2 = Dense(12,activation='relu', name='mg2')(merge1)
merge3 = Dense(13, name='mg3')
last_output = Dense(1, name='last')(merge2)

model = Model(inputs=[input1, input2], outputs=last_output)
model.summary()

#3 컴파일, 훈련
model.compile(loss = 'mse',optimizer='adam')
model.fit([x1_train,x2_train],y_train, epochs=10,batch_size=1)

#4 평가, 예측
loss = model.evaluate([x1_test,x2_test],y_test)
print('loss : ', loss)

# 월요일 시가 제목 000 xxxx원