package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.GroupData;
import model.RecordData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;


    public static void main(String[] args) throws IOException {
        Generator generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        Object data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            String json = mapper.writeValueAsString(data);
//            try (var writer = new FileWriter(output)) {
//                writer.write(json);
//            }

            mapper.writeValue(new File(output), data); // можно заменить кодом выше
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Unknown format " + format);
        }
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("records".equals(type)) {
            return generateRecords();
        } else {
            throw new IllegalArgumentException("Unknown type of data " + type);
        }
    }

    private Object generateRecords() {
        ArrayList<RecordData> records = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            records.add(new RecordData()
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withLastName(CommonFunctions.randomString(i * 10))
            );
        }
        return records;
    }

    private Object generateGroups() {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            groups.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10))
            );
        }
        return groups;
    }


}
