# -*- encoding: utf-8 -*-
require File.expand_path('../lib/debruijn/version', __FILE__)

Gem::Specification.new do |gem|
  gem.authors       = ["Daan van Berkel"]
  gem.email         = ["daan.v.berkel.1980@gmail.com"]
  gem.description   = %q{The debruijn gem provides a means to generate De Bruijn sequences.}
  gem.summary       = %q{De Bruijn sequence generator.}
  gem.homepage      = "https://github.com/dvberkel/debruijn"

  gem.add_development_dependency "rspec"

  gem.executables   = `git ls-files -- bin/*`.split("\n").map{ |f| File.basename(f) }
  gem.files         = `git ls-files`.split("\n")
  gem.test_files    = `git ls-files -- {test,spec,features}/*`.split("\n")
  gem.name          = "debruijn"
  gem.require_paths = ["lib"]
  gem.version       = DeBruijn::VERSION
end
