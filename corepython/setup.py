try:
    from setuptools import setup, find_packages
except ImportError:
    from ez_setup import use_setuptools
    use_setuptools()
    from setuptools import setup, find_packages

version = '1.0.0'

setup(name="corepython",
      version=version,
      description='simple python for cal service',
      long_description="""
cal service
""",
      classifiers=["Development Status :: 1 - Production/Stable",
                   "Intended Audience :: Developers",
                   "License :: OSI Approved :: BSD License",
                   "Programming Language :: Python",
                   "Topic :: Internet :: WWW/HTTP",
                   "Topic :: Software Development :: Libraries :: Python Modules",
                   ],
      author='aihua',
      author_email='aihua.sun@leador.com.cn',
      url='http://leador.com.cn',
      zip_safe=False,
      packages=find_packages(),
      install_requires=['tornado'],
      )
