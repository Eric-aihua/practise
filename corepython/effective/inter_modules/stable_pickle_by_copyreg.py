# encoding:utf-8
import copy_reg

__author__ = 'eric.sun'

import pickle

"pickle适合用来执行对象结构稳定，程序彼此互相信任的场景，当结构容易发生改变时，pickle在反序列化的时候就会出现一系列的问题。" \
"本代码演示如何通过使用copyreg实现可靠的pickle功能"

GAME_STATE_PATH = './gamestate'


class GameState(object):
    def __init__(self):
        self.level = 0
        self.lives = 4


class GameState2(object):
    def __init__(self, level=0, lives=4, points=100):
        self.level = level
        self.lives = lives
        self.points = points


# 该种写法的GameState的方法定义做出修改时（例如添加属性，修改类名等操作时），都不能被反序列化
def normal():
    state = GameState()
    with open(GAME_STATE_PATH, 'wb') as f:
        pickle.dump(state, f)

    with open(GAME_STATE_PATH, 'rb') as f:
        print pickle.load(f).__dict__


# 此种写法可以支持添加属性,但此时如果正常dump后，将points 从构造器中删除，此时load的话任然会报错
def support_add_prop():
    state = GameState2()
    # 使用copy_reg来实现注册pickle_game_state函数与GameState2的load行为的关联
    copy_reg.pickle(GameState2, pickle_game_state)
    with open(GAME_STATE_PATH, 'wb') as f:
        pickle.dump(state, f)

    with open(GAME_STATE_PATH, 'rb') as f:
        print pickle.load(f).__dict__


def pickle_game_state(gamestate):
    kargs = gamestate.__dict__
    return unpickle_game_state, (kargs,)


def unpickle_game_state(kargs):
    return GameState2(**kargs)


# 在support_add_prop 的基础上，通过版本管理的方案，来支持对属性的删除
def support_remove_prop():
    state = GameState2()
    copy_reg.pickle(GameState2, pickle_game_state_support_remove)
    with open(GAME_STATE_PATH, 'wb') as f:
        pickle.dump(state, f)

    with open(GAME_STATE_PATH, 'rb') as f:
        print pickle.load(f).__dict__


def pickle_game_state_support_remove(gamestate):
    kargs = gamestate.__dict__
    kargs['version'] = 2
    return unpickle_game_state_support_remove, (kargs,)


def unpickle_game_state_support_remove(kargs):
    version = kargs.pop('version', 1)
    # 使用不同的version对应不同的GameState2类结构版本，来控制构造GameState2时的kargs内容
    if version == 1:
        kargs.pop('points')
    return GameState2(**kargs)


if __name__ == '__main__':
    # normal()
    # support_add_prop()
    support_remove_prop()