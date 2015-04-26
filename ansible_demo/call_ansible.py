import ansible.runner

runner = ansible.runner.Runner(
   module_name='ping',
   module_args='',
   pattern='hadoop08',
   forks=10
)

datastructure = runner.run()
print(datastructure)
