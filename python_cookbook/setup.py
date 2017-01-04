try:
    from setuptools import setup, find_packages
except ImportError:
    from ez_setup import use_setuptools
    use_setuptools()
    from setuptools import setup, find_packages

version = '1.0.0'

setup(name="pythoncookbook",
      version=version,
      description='simple python for cal service',
      long_description="""cal service""",
      author='aihua',
      packages=find_packages(),
      install_requires=['tornado', 'requests','selenium','mechanize','kafka-python'],
      )
