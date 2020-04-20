class MenuItem {
  public route: string;
  public icon: string;
  public text: string;

  constructor(route: string, icon: string, text: string) {
    this.route = route;
    this.icon = icon;
    this.text = text;
  }
}

export const MENU: MenuItem[] = [
  {
    route: '/event',
    icon: 'plus-circle',
    text: 'Event Feed',
  },
  {
    route: '/event/create',
    icon: 'plus-circle',
    text: 'New Event',
  },
];
