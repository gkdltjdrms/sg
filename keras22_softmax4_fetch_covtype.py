import numpy as np  
from sklearn.datasets import fetch_covtype
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import pandas as pd
import time
import tensorflow as tf
from tensorflow.keras.utils import to_categorical
# categorical 을 사용할시 컬럼 삭제
# https://chat.openai.com/chat

#1. 데이터
datasets = fetch_covtype()  
x = datasets.data
y = datasets['target']
# print(x.shape, y.shape) #(581012, 54) (581012,)
# print(np.unique(y, return_counts=True))

# 1. keras to categorical===================================
# y = to_categorical(y)  # 원핫인코딩   #(581012, 8)
# print(type(y))  # <class 'numpy.ndarray'>
# print(y[:10])
# print(np.unique(y[:,0], return_counts=True))
# print(np.unique(y[:,1], return_counts=True))
# print('==================================')
# y = np.delete(y, 0 , axis = 1)
# print(y.shape)
# print(y[:10])
# print(np.unique(y[:,0], return_counts=True))

#===========================================================

#2.pandas get_dummies========================================
#  pd 에서는 인덱스와 헤더(컬럼,열)가 생성된다
# numpy자료형이 바로pandas를 못받아들인다

# y = pd.get_dummies(y)                   # .values , .numpy()  
# print(y)
# print(y[:10])
# print(type(y))    # <class 'pandas.core.frame.DataFrame'>
# y = y.to_numpy()   # y를 numpy로 변환 하는 두가지 방법
# # y = y.values
# print(type(y))    # <class 'numpy.ndarray'>
# print(y.shape)
# print(y.shape)
#===========================================================


#3. onehotencoder===========================================
from sklearn.preprocessing import OneHotEncoder
print(y.shape)
y = y.reshape(581012, 1)   # reshape는 데이터의 내용물이 바뀌면 안된다
print(y.shape)
ohe = OneHotEncoder()
y = ohe.fit_transform(y)
y=y.toarray()
#---------------------------------------------------------------

# # ohe.fit(y)
# # y = ohe.transform     # y = ohe.fit_transform(y) 한줄로 줄일수 있다
# print(y[:15])
# print(type(y))
# print(y.shape)

# y = y.reshape(-1, 1) 


# y=y.toarray() sparse = False: array 반환 
# (one-hot encoding에 필요한것은 array이므로)

# ===========================================================
# print(y.shape)
# print(y.shape)
# print(type(y))
x_train, x_test, y_train, y_test = train_test_split(
    x, y, shuffle=True,
    random_state=333,
    test_size= 0.2,
    stratify=y
)
# # print(x.shape, y.shape) #(581012, 54) (581012, 8) x// (581012, 54) (581012, 7)

#2. 모델 구성
model = Sequential()
model.add(Dense(50, activation='relu', input_shape=(54,)))
model.add(Dense(80, activation='relu'))
model.add(Dense(50, activation='relu'))
model.add(Dense(75, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(7, activation='softmax'))

#3. 컴파일, 훈련
from tensorflow.keras.callbacks import EarlyStopping 

earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=50,
                              restore_best_weights=True,
                              verbose=1)

model.compile(loss='categorical_crossentropy', optimizer='adam',
              metrics=['accuracy'])
start = time.time()

model.fit(x_train, y_train, epochs=2000, batch_size=2000,  
          validation_split=0.2, callbacks=[earlystopping],
          verbose=3)
end = time.time()
#4. 평가, 예측
loss, accuracy = model.evaluate(x_test, y_test)
print('loss :', loss)
print('accuracy :', accuracy)

y_predict = model.predict(x_test[:5])
# print(y_predict)


y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)   #axis=1 행에 있는것을 빼줌
print('y_pred(예측값) :', y_predict[:20])
y_test = np.argmax(y_test, axis=1)
print('y_test(원래값) :', y_test[:20])
acc = accuracy_score(y_test, y_predict)
print('acc :', acc)
print('걸린시간 :', end - start)

# 다중분류에서는 원핫인코딩을 한다
















