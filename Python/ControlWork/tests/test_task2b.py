import unittest
import task2b


class TestTask2b(unittest.TestCase):

    def test_solution(self):
        # test 1
        self.assertEqual("ремирП оготсорп атскет", task2b.solution("Пример простого текста"))
        # test 2
        self.assertEqual("ремирП оготсорп атскет И ен месвос оготсорп",
                         task2b.solution("Пример простого текста\n И не совсем простого"))
