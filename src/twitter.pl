use warnings;
use strict;
use diagnostics;
use WWW::Twitter;

foreach $i (@ARGV) {
  print $i; #Testing
}
# This is the actual code.
# my $twitter = WWW::Twitter->new(
  # username => $ARGV[0],
  # password => $ARGV[1]
# );
# $twitter->login();
# $twitter->tweet($ARGV[2]);
