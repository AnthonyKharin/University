import unittest
import task2a


class TestTask2a(unittest.TestCase):

    def test_solution(self):
        # test 1
        self.assertEqual("ремирП оготсорп атскет", task2a.solution("Пример простого текста"))
        # test 2
        self.assertEqual("ремирП оготсорп атскет И ен месвос оготсорп",
                         task2a.solution("Пример простого текста\n И не совсем простого"))
