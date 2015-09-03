use warnings;
use strict;
use diagnostics;
use WWW::Twitter;

$ENV {PERL_LWP_SSL_VERIFY_HOSTNAME} = 0; #This is terrible.

my $twitter = WWW::Twitter->new(
  username => $ARGV[0],
  password => $ARGV[1]
);
$twitter->login();
$twitter->tweet($ARGV[2]);
exit;
