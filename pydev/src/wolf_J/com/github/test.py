'''
Created on 2017年11月26日

@author: wolf_J
'''

class MyClass(object):
    '''
    classdocs
    '''
    __i = 10

    def __init__(self, i):
        '''
        Constructor
        '''
        self.__i = i
        
    def __str__(self, *args, **kwargs):
        return str(self.__i)
        
    def __del__(self):
        del self
        
MyClass1 = MyClass(15)
print(MyClass1)
print(repr(MyClass1))