module plannerLogic {
    requires java.sql;
    requires org.postgresql.jdbc;

    exports sashtgnv.plannerLogic.dates;
    exports sashtgnv.plannerLogic.database;
}