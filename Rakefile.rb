#!/usr/bin/env rake

require "rubygems"
require "bundler/setup"

require 'rspec/core/rake_task'

task :default => [:test]

desc "Run rspec on the spec directory"
RSpec::Core::RakeTask.new('test') do |task|
  task.rspec_opts = ['-I lib -I lib/debruijn']
  task.pattern = 'spec/**/*_spec.rb'
end
