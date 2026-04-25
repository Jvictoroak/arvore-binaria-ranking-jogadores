void main() {
    Player player = new Player("nickname01", 100);
    System.out.println(player.getNickname());
    System.out.println(player.getRanking());
    player.setNickname("nickname01_edit");
    player.setRanking(1);
    System.out.println(player.getNickname());
    System.out.println(player.getRanking());
}
