package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthInfo;
import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

public class CinemaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder encoder;

    /* User */
    public void saveUser(final User user) {
        String INSERT_USER_QUERY = "insert into cinema_ex00.cinema.t_user (name, family, email, phone_number, password) " +
                " VALUES (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER_QUERY);
            ps.setString(1, user.getName());
            ps.setString(2, user.getFamily());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, encoder.encode(user.getPassword()));
            return ps;
        }, keyHolder);
    }

    public User getUserForEmail(String email) {
        String SELECT_USER_QUERY = "select * from cinema_ex00.cinema.t_user where email=? limit 1";
        return jdbcTemplate.queryForObject(
                SELECT_USER_QUERY,
                (rs, rowNum) -> {
                    User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setFamily(rs.getString("family"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    return user;
                },
                email);
    }

    /* Auth info */
    public List<AuthInfo> getAuthInfos(String email) {
        String SELECT_AUTH_INFO_QUERY = "select * from cinema_ex00.cinema.t_auth_info " +
                " where \"user\"=(select user_id from cinema_ex00.cinema.t_user where email=? limit 1)";
        return jdbcTemplate.query(
                SELECT_AUTH_INFO_QUERY,
                (rs, rowNum)-> {
                    AuthInfo authInfo = new AuthInfo();
                    authInfo.setIp(rs.getString("ip"));
                    authInfo.setTime(rs.getLong("time"));
                    return authInfo;
                },
                email);
    }

    public void saveAuthInfo(AuthInfo authInfo) {
        String INSERT_AUTH_INFO_QUERY = "insert into cinema_ex00.cinema.t_auth_info (time, \"user\", ip) " +
                " values (?, (select user_id from cinema_ex00.cinema.t_user where email=? limit 1), ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_AUTH_INFO_QUERY);
            ps.setLong(1, authInfo.getTime());
            ps.setString(2, authInfo.getUser().getEmail());
            ps.setString(3, authInfo.getIp());
            return ps;
        }, keyHolder);
    }

    /* Images */
    public long saveImage(Image image) {
        String INSERT_IMAGE_QUERY = "insert into cinema_ex00.cinema.t_image (\"user\", size, mime, name) " +
                " VALUES ((select user_id from cinema_ex00.cinema.t_user where email=? limit 1), ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                INSERT_IMAGE_QUERY, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        preparedStatementCreatorFactory.setGeneratedKeysColumnNames("image_id");
        jdbcTemplate.update(preparedStatementCreatorFactory.newPreparedStatementCreator(Arrays.asList(
                image.getUser().getEmail(),
                image.getSize(),
                image.getMime(),
                image.getName())),
                keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Image> getImages(String email) {
        String SELECT_IMAGE_QUERY = "select * from cinema_ex00.cinema.t_image " +
                " where \"user\"=(select user_id from cinema_ex00.cinema.t_user where email=? limit 1)";
        return jdbcTemplate.query(
                SELECT_IMAGE_QUERY,
                (rs, rowNum) -> getImageFromResultSet(rs),
                email);
    }

    public Image getImage(String imageId) {
        String SELECT_IMAGE_QUERY = "select * from cinema_ex00.cinema.t_image " +
                " where image_id=?";
        long id;
        try {
            id = Long.parseLong(imageId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return jdbcTemplate.queryForObject(
                SELECT_IMAGE_QUERY,
                (rs, rowNum) -> getImageFromResultSet(rs),
                id);
    }

    private Image getImageFromResultSet(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setSize(rs.getLong("size"));
        image.setMime(rs.getString("mime"));
        image.setId(rs.getLong("image_id"));
        image.setName(rs.getString("name"));
        return image;
    }
}
